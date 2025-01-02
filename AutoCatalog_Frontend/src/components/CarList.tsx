import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { MultiSelect } from "primereact/multiselect";
import "primereact/resources/themes/lara-light-indigo/theme.css"; // Import a theme
import "primereact/resources/primereact.min.css"; // Import core styles
import "primeicons/primeicons.css"; // Import prime icons

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

interface Feature {
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
  const [features, setFeatures] = useState<Feature[]>([]);
  const [selectedFeatures, setSelectedFeatures] = useState<number[]>([]);
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [totalPages, setTotalPages] = useState<number>(0);
  const [elementsPerPage] = useState<number>(3);
  const [pendingFilters, setPendingFilters] = useState({
    brandId: "all",
    modelId: "all",
  });
  const [appliedFilters, setAppliedFilters] = useState({
    brandId: "all",
    modelId: "all",
    features: [] as number[],
  });

  const fetchCars = async (page: number) => {
    const queryParameters = new URLSearchParams();
    queryParameters.append("currentPage", page.toString());
    queryParameters.append("elementsPerPage", elementsPerPage.toString());

    if (appliedFilters.brandId !== "all") {
      queryParameters.append("brandId", appliedFilters.brandId);
    }

    if (appliedFilters.modelId !== "all") {
      queryParameters.append("modelId", appliedFilters.modelId);
    }

    if (appliedFilters.features.length > 0) {
      queryParameters.append("features", appliedFilters.features.join(","));
    }

    try {
      const response = await fetch(
        `http://localhost:8080/api/car/filter?${queryParameters.toString()}`
      );
      if (!response.ok) {
        throw new Error("Failed to fetch cars");
      }
      const data: PaginatedResponse = await response.json();
      setCars(data.cars);
      setTotalPages(data.totalPages);
    } catch (error) {
      console.error(error);
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
    } catch (error) {
      console.error(error);
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
    } catch (error) {
      console.error(error);
    }
  };

  const fetchFeatures = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/feature/fetch");
      if (!response.ok) {
        throw new Error("Failed to fetch features");
      }
      const data: Feature[] = await response.json();
      setFeatures(data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchCars(currentPage);
  }, [currentPage, appliedFilters]);

  useEffect(() => {
    fetchBrands();
    fetchFeatures();
  }, []);

  const handleFilterChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const { name, value } = event.target;
    setPendingFilters((prev) => ({
      ...prev,
      [name]: value,
    }));

    if (name === "brandId") {
      fetchModels(value);
    }
  };

  const handleFeatureSelection = (selectedValues: number[]) => {
    setSelectedFeatures(selectedValues);
  };

  const applyFilters = () => {
    setAppliedFilters({
      ...pendingFilters,
      features: selectedFeatures,
    });
    setCurrentPage(1);
  };

  return (
    <div className="container mt-4">
      <h2>Car Catalog</h2>
      <div className="row mb-3">
        <div className="col-md-3">
          <label>Brand</label>
          <select
            className="form-select"
            name="brandId"
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
        <div className="col-md-3">
          <label>Model</label>
          <select
            className="form-select"
            name="modelId"
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
        <div className="col-md-3">
          <label>Features</label>
          <MultiSelect
            value={selectedFeatures}
            options={features.map((feature) => ({
              label: feature.name,
              value: feature.id,
            }))}
            onChange={(e) => handleFeatureSelection(e.value)}
            placeholder="Select Features"
            display="chip"
            optionLabel="label"
            optionValue="value"
            className="w-full"
            filter
          />
        </div>
        <div className="col-md-3 d-flex align-items-end">
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
          className="btn btn-secondary me-2"
          onClick={() => setCurrentPage(currentPage - 1)}
          disabled={currentPage === 1}
        >
          Previous
        </button>
        <span>
          Page {currentPage} of {totalPages}
        </span>
        <button
          className="btn btn-secondary ms-2"
          onClick={() => setCurrentPage(currentPage + 1)}
          disabled={currentPage === totalPages}
        >
          Next
        </button>
      </div>
    </div>
  );
}

export default CarList;
