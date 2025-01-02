import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import carPlaceholder from "./../assets/carPlaceholder.png";

interface Car {
  id: number;
  title: string;
  price: number;
  yearManufactured: number;
  fuel: string;
  kilometers?: number;
}

interface PaginatedResponse {
  cars: Car[];
  totalPages: number;
  totalElements: number;
}

function CarList() {
  const [cars, setCars] = useState<Car[]>([]);
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [totalPages, setTotalPages] = useState<number>(0);
  const [elementsPerPage] = useState<number>(3);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const fetchCars = async (page: number) => {
    setLoading(true);
    setError(null);

    try {
      const response = await fetch(
        `http://localhost:8080/api/car/filter?currentPage=${page}&elementsPerPage=${elementsPerPage}`
      );

      if (!response.ok) {
        throw new Error("Failed to fetch cars");
      }

      const data: PaginatedResponse = await response.json();

      console.log(data);

      setCars(data.cars);
      setTotalPages(data.totalPages);
      setLoading(false);
    } catch (err) {
      setError(err instanceof Error ? err.message : "Unknown error");
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchCars(currentPage);
  }, [currentPage]);

  const goToPage = (page: number) => {
    if (page > 0 && page <= totalPages) {
      setCurrentPage(page);
    }
  };

  if (loading) {
    return <p>Loading cars...</p>;
  }

  if (error) {
    return <p>Error: {error}</p>;
  }

  return (
    <>
      {cars.length === 0 && <h2>No cars</h2>}
      <div className="container mt-4">
        <div className="row">
          {cars.map((car) => (
            <div key={car.id} className="col-md-4 mb-4">
              <div className="card">
                <img
                  className="card-img-top"
                  src={carPlaceholder}
                  alt={`Image of ${car.title}`}
                />
                <div className="card-body">
                  <h5 className="card-title">
                    {car.title} - {car.price} lv.
                  </h5>
                  <p className="card-text">
                    {car.yearManufactured}, {car.fuel}, {car.kilometers} KM
                  </p>
                  <Link to={`/car/${car.id}`} className="btn btn-primary">
                    View Details
                  </Link>
                </div>
              </div>
            </div>
          ))}
        </div>
        <div className="d-flex justify-content-center mt-4">
          <button
            className="btn btn-secondary"
            onClick={() => goToPage(currentPage - 1)}
            disabled={currentPage === 1}
          >
            Previous
          </button>
          <span>
            Page {currentPage} of {totalPages}
          </span>
          <button
            className="btn btn-secondary"
            onClick={() => goToPage(currentPage + 1)}
            disabled={currentPage === totalPages}
          >
            Next
          </button>
        </div>
      </div>
    </>
  );
}

export default CarList;
