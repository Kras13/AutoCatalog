import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import CarList from "./components/CarList";
import CarDetails from "./components/CarDetails";

function App() {
  const cars = [
    {
      id: 1,
      title: "Audi A4 2.0 TDI",
      price: 15000,
      yearManufactured: 2002,
      fuel: "Diesel",
      kilometers: 200000,
    },
    {
      id: 2,
      title: "Toyota Rav-4",
      price: 7500,
      yearManufactured: 1998,
      fuel: "Petrol",
      kilometers: 300000,
    },
    {
      id: 3,
      title: "Honda Accord 2.4I",
      price: 12000,
      yearManufactured: 2007,
      fuel: "Petrol",
      kilometers: 200000,
    },
  ];

  return (
    <Router>
      <Routes>
        <Route path="/" element={<CarList />} />
        <Route path="/car/:carId" element={<CarDetails />} />
      </Routes>
    </Router>
  );
}

export default App;
