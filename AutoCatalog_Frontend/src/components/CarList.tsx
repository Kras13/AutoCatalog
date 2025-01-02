import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

interface Car {
  id: number;
  title: string;
  price: number;
  yearManufactured: number;
  fuel: string;
  kilometers: number;
  imageUrl?: string;
}

interface Brand {
  id: number;
  name: string;
}

interface Model {
  id: number;
  name: string;
}

interface PaginatedResponse {
  cars: Car[];
  totalPages: number;
  totalElements: number;
}

function CarList() {
  const [cars, setCars] = useState<Car[]>([]);
  const [brands, setBrands] = useState<Brand[]>([]);
  const [models, setModels] = useState<Model[]>([]);
  const [totalPages, setTotalPages] = useState<number>(0);
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [elementsPerPage] = useState<number>(3);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  // Filters
  const [pendingFilters, setPendingFilters] = useState({
    brandId: "all",
    modelId: "all",
    transmissionId: "all",
  });
  const [appliedFilters, setAppliedFilters] = useState({
    brandId: "all",
    modelId: "all",
    transmissionId: "all",
  });

  const fetchCars = async (page: number) => {
    setLoading(true);
    setError(null);

    try {
      const params = new URLSearchParams({
        currentPage: page.toString(),
        elementsPerPage: elementsPerPage.toString(),
      });

      if (appliedFilters.brandId !== "all") {
        params.append("brandId", appliedFilters.brandId);
      }

      if (appliedFilters.modelId !== "all") {
        params.append("modelId", appliedFilters.modelId);
      }

      const response = await fetch(
        `http://localhost:8080/api/car/filter?${params.toString()}`
      );
      if (!response.ok) {
        throw new Error("Failed to fetch cars");
      }

      const data: PaginatedResponse = await response.json();
      setCars(data.cars);
      setTotalPages(data.totalPages);
    } catch (err) {
      setError(err instanceof Error ? err.message : "Unknown error");
    } finally {
      setLoading(false);
    }
  };

  const fetchBrands = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/brand/fetch");
      if (!response.ok) {
        throw new Error("Failed to fetch brands");
      }
      const data: Brand[] = await response.json();
      setBrands(data);
    } catch (err) {
      setError(err instanceof Error ? err.message : "Unknown error");
    }
  };

  const fetchModels = async (brandId: string) => {
    if (brandId === "all") {
      setModels([]);
      return;
    }
    try {
      const response = await fetch(
        `http://localhost:8080/api/model/${brandId}`
      );
      if (!response.ok) {
        throw new Error("Failed to fetch models");
      }
      const data: Model[] = await response.json();
      setModels(data);
    } catch (err) {
      setError(err instanceof Error ? err.message : "Unknown error");
    }
  };

  useEffect(() => {
    fetchCars(currentPage);
  }, [currentPage, appliedFilters]);

  useEffect(() => {
    fetchBrands();
  }, []);

  const handleFilterChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const { name, value } = event.target;
    setPendingFilters((prev) => ({
      ...prev,
      [name]: value,
    }));

    if (name === "brandId") {
      setPendingFilters((prev) => ({
        ...prev,
        modelId: "all",
      }));
      fetchModels(value);
    }
  };

  const applyFilters = () => {
    setAppliedFilters({ ...pendingFilters });
    setCurrentPage(1);
  };

  if (loading) {
    return <p>Loading cars...</p>;
  }

  if (error) {
    return <p>Error: {error}</p>;
  }

  return (
    <div className="container mt-4">
      <h2>Car Catalog</h2>
      <div className="row mb-4">
        <div className="col-md-4">
          <label htmlFor="brandFilter" className="form-label">
            Brand
          </label>
          <select
            id="brandFilter"
            name="brandId"
            className="form-select"
            value={pendingFilters.brandId}
            onChange={handleFilterChange}
          >
            <option value="all">All</option>
            {brands.map((brand) => (
              <option key={brand.id} value={brand.id}>
                {brand.name}
              </option>
            ))}
          </select>
        </div>
        <div className="col-md-4">
          <label htmlFor="modelFilter" className="form-label">
            Model
          </label>
          <select
            id="modelFilter"
            name="modelId"
            className="form-select"
            value={pendingFilters.modelId}
            onChange={handleFilterChange}
          >
            <option value="all">All</option>
            {models.map((model) => (
              <option key={model.id} value={model.id}>
                {model.name}
              </option>
            ))}
          </select>
        </div>
        <div className="col-md-4 d-flex align-items-end">
          <button className="btn btn-primary" onClick={applyFilters}>
            Apply Filters
          </button>
        </div>
      </div>

      <div className="row">
        {cars.map((car) => (
          <div key={car.id} className="col-md-4 mb-4">
            <div className="card">
              <img
                className="card-img-top"
                src={car.imageUrl || "placeholder.jpg"}
                alt={`Image of ${car.title}`}
              />
              <div className="card-body">
                <h5 className="card-title">
                  {car.title} - {car.price} lv.
                </h5>
                <p className="card-text">
                  {car.yearManufactured}, {car.fuel}, {car.kilometers || "N/A"}{" "}
                  KM
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
          onClick={() => setCurrentPage(currentPage - 1)}
          disabled={currentPage === 1}
        >
          Previous
        </button>
        <span>
          Page {currentPage} of {totalPages}
        </span>
        <button
          className="btn btn-secondary"
          onClick={() => setCurrentPage(currentPage + 1)}
          disabled={currentPage >= totalPages}
        >
          Next
        </button>
      </div>
    </div>
  );
}

export default CarList;
