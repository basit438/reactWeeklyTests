import React, { useState, useEffect } from "react";
import AddPersonForm from "./components/AddPersonForm";
import PersonTable from "./components/PersonTable";
import SearchForm from "./components/SearchForm";
import Tabs from "./components/Tabs";
import "./App.css";

function App() {
  const [activeTab, setActiveTab] = useState("add"); // Default tab
  const [people, setPeople] = useState(() => {
    const storedPeople = localStorage.getItem("people");
    return storedPeople ? JSON.parse(storedPeople) : [];
  });

  const [newPerson, setNewPerson] = useState({
    name: "",
    dob: "",
    aadhar: "",
    mobile: "",
    age: ""
  });

  const [searchAadhar, setSearchAadhar] = useState("");
  const [searchResult, setSearchResult] = useState(null);

  const calculateAge = (dob) => {
    const birthDate = new Date(dob);
    const diff = Date.now() - birthDate.getTime();
    const ageDate = new Date(diff);
    return Math.abs(ageDate.getUTCFullYear() - 1970);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewPerson((prev) => ({
      ...prev,
      [name]: value,
      age: name === "dob" ? calculateAge(value) : prev.age
    }));
  };

  const handleAddPerson = () => {
    if (!newPerson.name || !newPerson.dob || !newPerson.aadhar || !newPerson.mobile) {
      alert("Please fill all fields.");
      return;
    }

    if (newPerson.aadhar.length !== 12 || newPerson.mobile.length !== 10) {
      alert("Aadhar must be 12 digits and mobile must be 10 digits.");
      return;
    }

    const updatedPeople = [...people, newPerson];
    setPeople(updatedPeople);
    localStorage.setItem("people", JSON.stringify(updatedPeople));

    setNewPerson({
      name: "",
      dob: "",
      aadhar: "",
      mobile: "",
      age: ""
    });
  };

  const handleDeletePerson = (aadhar) => {
    const updatedPeople = people.filter((person) => person.aadhar !== aadhar);
    setPeople(updatedPeople);
    localStorage.setItem("people", JSON.stringify(updatedPeople));
  };

  const handleSearch = () => {
    const foundPerson = people.find((person) => person.aadhar === searchAadhar);
    if (foundPerson) {
      setSearchResult(foundPerson);
    } else {
      setSearchResult(null);
      alert("No match found");
    }
  };

  return (
    <div className="container mx-auto p-4">
      <Tabs activeTab={activeTab} setActiveTab={setActiveTab} />

      {activeTab === "add" && (
        <div>
          <AddPersonForm
            newPerson={newPerson}
            handleInputChange={handleInputChange}
            handleAddPerson={handleAddPerson}
          />
          <PersonTable people={people} handleDeletePerson={handleDeletePerson} />
        </div>
      )}

      {activeTab === "retrieve" && (
        <div>
          <SearchForm
            searchAadhar={searchAadhar}
            setSearchAadhar={setSearchAadhar}
            handleSearch={handleSearch}
            searchResult={searchResult}
          />
        </div>
      )}
    </div>
  );
}

export default App;
