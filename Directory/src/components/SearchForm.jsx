import React from "react";

function SearchForm({
  searchAadhar,
  setSearchAadhar,
  handleSearch,
  searchResult
}) {
  return (
    <div className="p-4 bg-white shadow-md rounded-md">
      <h2 className="text-xl font-bold mb-4">Retrieve Information</h2>
      <div className="mb-4">
        <input
          type="text"
          value={searchAadhar}
          onChange={(e) => setSearchAadhar(e.target.value)}
          placeholder="Enter Aadhar Number"
          className="border p-1 rounded mr-2"
        />
        <button onClick={handleSearch} className="bg-blue-500 text-white px-2 py-1 rounded">
          Search
        </button>
      </div>

      {searchResult ? (
        <table className="table-auto w-full bg-white shadow-md rounded-md">
          <thead>
            <tr>
              <th>Name</th>
              <th>Date of Birth</th>
              <th>Aadhar Number</th>
              <th>Mobile Number</th>
              <th>Age</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{searchResult.name}</td>
              <td>{searchResult.dob}</td>
              <td>{searchResult.aadhar}</td>
              <td>{searchResult.mobile}</td>
              <td>{searchResult.age}</td>
            </tr>
          </tbody>
        </table>
      ) : (
        <p>No person found.</p>
      )}
    </div>
  );
}

export default SearchForm;
