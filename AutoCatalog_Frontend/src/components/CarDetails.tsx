import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

interface Car {
  id: number;
  imageUrl: string;
  price: number;
  description: string;
  userName: string;
  phone: string;
}

const CarDetails: React.FC = () => {
  const { carId } = useParams<{ carId: string }>();
  const [car, setCar] = useState<Car | null>(null);

  useEffect(() => {
    const fetchCarDetails = async () => {
      const carData: Car = {
        id: parseInt(carId || "0"),
        imageUrl: "https://via.placeholder.com/400",
        price: 15000,
        description: "A detailed description of the car...",
        userName: "John Doe",
        phone: "123-456-7890",
      };
      setCar(carData);
    };

    fetchCarDetails();
  }, [carId]);

  if (!car) {
    return <div>Loading...</div>;
  }

  return (
    <div className="container mt-4">
      <div className="row">
        <div className="col-md-6">
          <img src={car.imageUrl} alt="Car" className="img-fluid" />
        </div>
        <div className="col-md-6">
          <h2>Price: ${car.price}</h2>
          <h4>Description:</h4>
          <p>{car.description}</p>
          <h4>Contact:</h4>
          <p>Name: {car.userName}</p>
          <p>Phone: {car.phone}</p>
        </div>
      </div>
    </div>
  );
};

export default CarDetails;
