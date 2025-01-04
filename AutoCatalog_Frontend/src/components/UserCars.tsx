import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { MultiSelect } from "primereact/multiselect";
import "primereact/resources/themes/lara-light-indigo/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";

interface Car {
  id: number;
  title: string;
  price: number;
  yearManufactured: number;
  fuel: string;
  kilometers: number;
  imageUrl?: string;
}

function UserCars() {
  const [cars, setCars] = useState<Car[]>([]);

  const fetchCars = async () => {
    try {
      const token = localStorage.getItem("jwtToken");
      const response = await fetch(`http://localhost:8080/api/user/cars`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });

      if (!response.ok) {
        throw new Error("Failed to fetch cars: " + response.text);
      }

      const data: Car[] = await response.json();

      setCars(data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchCars();
  }, []);

  return (
    <div className="container mt-4">
      <h2>Car Catalog</h2>

      <div className="row" style={{ marginTop: "80px" }}>
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
                  {car.yearManufactured}, {car.fuel}, {car.kilometers} KM
                </p>
                <Link to={`/edit/car/${car.id}`} className="btn btn-primary">
                  Edit
                </Link>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default UserCars;
