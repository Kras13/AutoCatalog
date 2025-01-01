// src/components/CarList.tsx
import React from "react";
import { Link } from "react-router-dom";

interface Car {
  id: number;
  imageUrl: string;
  price: number;
  shortDescription: string;
}

interface CarListProps {
  cars: Car[];
}

const CarList: React.FC<CarListProps> = ({ cars }) => {
  return (
    <div className="container mt-4">
      <h2>Car Catalog</h2>
      <div className="row">
        {cars.map((car) => (
          <div key={car.id} className="col-md-4 mb-4">
            <div className="card">
              <img src={car.imageUrl} className="card-img-top" alt="Car" />
              <div className="card-body">
                <h5 className="card-title">Price: ${car.price}</h5>
                <p className="card-text">{car.shortDescription}</p>
                <Link to={`/car/${car.id}`} className="btn btn-primary">
                  View Details
                </Link>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default CarList;
