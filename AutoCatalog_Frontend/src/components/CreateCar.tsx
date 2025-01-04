import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import CarForm from "./CarForm";

// import React, { useState, useEffect } from "react";
// import { MultiSelect } from "primereact/multiselect";

// interface Brand {
//   id: number;
//   name: string;
// }

// interface Model {
//   id: number;
//   name: string;
// }

// interface Transmission {
//   id: number;
//   name: string;
// }

// interface Fuel {
//   id: number;
//   name: string;
// }

// interface Feature {
//   id: number;
//   name: string;
// }

// interface Category {
//   id: number;
//   name: string;
// }

// const CreateCar = () => {
//   const [formData, setFormData] = useState({
//     modelId: "",
//     title: "",
//     description: "",
//     price: "",
//     dateManufactured: "",
//     categoryId: "",
//     fuelId: "",
//     transmissionId: "",
//     features: [],
//   });
//   const [selectedBrand, setSelectedBrand] = useState("");

//   const [brands, setBrands] = useState<Brand[]>([]);
//   const [models, setModels] = useState<Model[]>([]);
//   const [transmissions, setTransmissions] = useState<Transmission[]>([]);
//   const [fuels, setFuels] = useState<Fuel[]>([]);
//   const [categories, setCategories] = useState<Category[]>([]);
//   const [features, setFeatures] = useState<Feature[]>([]);

//   const [message, setMessage] = useState<string | null>(null);
//   const [loading, setLoading] = useState(false);

//   const fetchBrands = async () => {
//     try {
//       const response = await fetch("http://localhost:8080/api/brand/fetch");

//       if (!response.ok) {
//         throw new Error("Failed to fetch brands");
//       }

//       const data: Brand[] = await response.json();

//       setBrands(data);
//     } catch (error) {
//       console.error(error);
//     }
//   };

//   const fetchModels = async (brandId: string) => {
//     try {
//       const response = await fetch(
//         `http://localhost:8080/api/model/${brandId}`
//       );

//       if (!response.ok) {
//         throw new Error("Failed to fetch models");
//       }

//       const data: Model[] = await response.json();

//       setModels(data);
//     } catch (error) {
//       console.error(error);
//     }
//   };

//   const fetchTransmissions = async () => {
//     try {
//       const response = await fetch(
//         "http://localhost:8080/api/transmission/fetch"
//       );

//       if (!response.ok) {
//         throw new Error("Failed to fetch brands");
//       }

//       const data: Transmission[] = await response.json();

//       setTransmissions(data);
//     } catch (error) {
//       console.error(error);
//     }
//   };

//   const fetchFuels = async () => {
//     try {
//       const response = await fetch("http://localhost:8080/api/fuel/fetch");

//       if (!response.ok) {
//         throw new Error("Failed to fetch brands");
//       }

//       const data: Fuel[] = await response.json();

//       setFuels(data);
//     } catch (error) {
//       console.error(error);
//     }
//   };

//   const fetchCategories = async () => {
//     try {
//       const response = await fetch("http://localhost:8080/api/category/fetch");

//       if (!response.ok) {
//         throw new Error("Failed to fetch brands");
//       }

//       const data: Category[] = await response.json();

//       setCategories(data);
//     } catch (error) {
//       console.error(error);
//     }
//   };

//   const fetchFeatures = async () => {
//     try {
//       const response = await fetch("http://localhost:8080/api/feature/fetch");
//       if (!response.ok) {
//         throw new Error("Failed to fetch features");
//       }
//       const data: Feature[] = await response.json();
//       setFeatures(data);
//     } catch (error) {
//       console.error(error);
//     }
//   };

//   useEffect(() => {
//     fetchBrands();
//     fetchTransmissions();
//     fetchFuels();
//     fetchCategories();
//     fetchFeatures();
//   }, []);

//   const handleSelectChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
//     const { name, value } = event.target;

//     if (name === "brandId") {
//       setSelectedBrand(event.target.value);
//       fetchModels(value);

//       return;
//     }

//     setFormData((prev) => ({
//       ...prev,
//       [name]: value,
//     }));
//   };

//   const handleInputChange = (
//     event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
//   ) => {
//     const { name, value } = event.target;

//     setFormData((prev) => ({ ...prev, [name]: value }));
//   };

//   const handleFeatureSelection = (selectedValues: any) => {
//     // todo
//     setFormData((prev) => ({ ...prev, features: selectedValues }));
//   };

//   const handleSubmit = async (event: React.FormEvent) => {
//     event.preventDefault();
//     setLoading(true);
//     setMessage(null);

//     try {
//       const token = localStorage.getItem("jwtToken");
//       if (!token) {
//         throw new Error("User is not authenticated. Please log in.");
//       }

//       const response = await fetch("http://localhost:8080/api/car/create", {
//         method: "POST",
//         headers: {
//           "Content-Type": "application/json",
//           Authorization: `Bearer ${token}`,
//         },
//         body: JSON.stringify(formData),
//       });

//       if (!response.ok) {
//         const errorData = await response.text();

//         throw new Error(errorData || "Car creation failed");
//       }

//       setMessage("Car created successfully!");
//       setFormData({
//         modelId: "",
//         title: "",
//         description: "",
//         price: "",
//         dateManufactured: "",
//         categoryId: "",
//         fuelId: "",
//         transmissionId: "",
//         features: [],
//       });
//     } catch (error) {
//       setMessage(
//         error instanceof Error ? error.message : "Unknown error occurred"
//       );
//     } finally {
//       setLoading(false);
//     }
//   };

//   return (
//     <div className="container mt-4">
//       <h2>Create Car</h2>
//       {message && (
//         <div
//           className={`alert ${
//             message.includes("successfully") ? "alert-success" : "alert-danger"
//           }`}
//         >
//           {message}
//         </div>
//       )}

//       <div className="col-md-3 mt-3">
//         <label>Brand</label>
//         <select
//           className="form-select"
//           name="brandId"
//           value={selectedBrand}
//           onChange={handleSelectChange}
//           required
//         >
//           <option value="-1"></option>
//           {brands.map((brand) => (
//             <option key={brand.id} value={brand.id}>
//               {brand.name}
//             </option>
//           ))}
//         </select>
//       </div>

//       <div className="col-md-6 mb-3">
//         <label>Model</label>
//         <select
//           className="form-select"
//           name="modelId"
//           value={formData.modelId}
//           onChange={handleSelectChange}
//         >
//           <option value="-1"></option>
//           {models.map((model) => (
//             <option key={model.id} value={model.id}>
//               {model.name}
//             </option>
//           ))}
//         </select>
//       </div>

//       <div className="col-md-3 mt-3">
//         <label>Category</label>
//         <select
//           className="form-select"
//           name="categoryId"
//           value={formData.categoryId}
//           onChange={handleSelectChange}
//         >
//           <option value="-1"></option>
//           {categories.map((category) => (
//             <option key={category.id} value={category.id}>
//               {category.name}
//             </option>
//           ))}
//         </select>
//       </div>

//       <div className="col-md-3 mt-3">
//         <label>Fuel</label>
//         <select
//           className="form-select"
//           name="fuelId"
//           value={formData.fuelId}
//           onChange={handleSelectChange}
//         >
//           <option value="-1"></option>
//           {fuels.map((fuel) => (
//             <option key={fuel.id} value={fuel.id}>
//               {fuel.name}
//             </option>
//           ))}
//         </select>
//       </div>

//       <div className="col-md-3 mt-3">
//         <label>Transmission</label>
//         <select
//           className="form-select"
//           name="transmissionId"
//           value={formData.transmissionId}
//           onChange={handleSelectChange}
//         >
//           <option value="-1"></option>
//           {transmissions.map((transmission) => (
//             <option key={transmission.id} value={transmission.id}>
//               {transmission.name}
//             </option>
//           ))}
//         </select>
//       </div>

//       <div className="mb-3">
//         <label htmlFor="features" className="form-label">
//           Features
//         </label>
//         <MultiSelect
//           value={formData.features}
//           options={features.map((feature: any) => ({
//             label: feature.name,
//             value: feature.id,
//           }))}
//           onChange={(e) => handleFeatureSelection(e.value)}
//           placeholder="Select features"
//           className="form-control"
//         />
//       </div>

//       <div className="col-md-6 mb-3">
//         <label htmlFor="title" className="form-label">
//           Title
//         </label>
//         <input
//           type="text"
//           className="form-control"
//           id="title"
//           name="title"
//           value={formData.title}
//           onChange={handleInputChange}
//           required
//         />
//       </div>

//       <div className="col-md-6 mb-3">
//         <label htmlFor="description" className="form-label">
//           Description
//         </label>
//         <textarea
//           className="form-control"
//           id="description"
//           name="description"
//           value={formData.description}
//           onChange={handleInputChange}
//           rows={3}
//         />
//       </div>

//       <div className="col-md-6 mb-3">
//         <label htmlFor="price" className="form-label">
//           Price
//         </label>
//         <input
//           type="number"
//           className="form-control"
//           id="price"
//           name="price"
//           value={formData.price}
//           onChange={handleInputChange}
//           required
//         />
//       </div>

//       <div className="col-md-6 mb-3">
//         <label htmlFor="dateManufactured" className="form-label">
//           Date Manufactured
//         </label>
//         <input
//           type="date"
//           className="form-control"
//           id="dateManufactured"
//           name="dateManufactured"
//           value={formData.dateManufactured}
//           onChange={handleInputChange}
//           required
//         />
//       </div>

//       <form onSubmit={handleSubmit}>
//         <button type="submit" className="btn btn-primary" disabled={loading}>
//           {loading ? "Creating..." : "Create Car"}
//         </button>
//       </form>
//     </div>
//   );
// };

// export default CreateCar;

const CreateCar: React.FC = () => {
  const handleCreateSubmit = async (data: any) => {
    try {
      const token = localStorage.getItem("jwtToken");
      const response = await fetch("http://localhost:8080/api/car/create", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(data),
      });

      if (!response.ok) {
        throw new Error("Failed to create car");
      }

      alert("Car created successfully!");
    } catch (error) {
      alert(error instanceof Error ? error.message : "Unknown error occurred");
    }
  };

  return (
    <div>
      <CarForm mode="create" carData={null} onSubmit={handleCreateSubmit} />
    </div>
  );
};

export default CreateCar;
