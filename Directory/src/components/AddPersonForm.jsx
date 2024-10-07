import React from "react";

function AddPersonForm({ newPerson, handleInputChange, handleAddPerson }) {
  return (
    <div className="p-4 bg-white shadow-md rounded-md">
      <h2 className="text-xl font-bold mb-4">Add New Person</h2>
      <table className="table-auto w-full mb-4">
        <thead>
          <tr>
            <th>Name</th>
            <th>Date of Birth</th>
            <th>Aadhar Number</th>
            <th>Mobile Number</th>
            <th>Age</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>
              <input
                type="text"
                name="name"
                value={newPerson.name}
                onChange={handleInputChange}
                placeholder="Name"
                className="border p-1 rounded"
              />
            </td>
            <td>
              <input
                type="date"
                name="dob"
                value={newPerson.dob}
                onChange={handleInputChange}
                className="border p-1 rounded"
              />
            </td>
            <td>
              <input
                type="text"
                name="aadhar"
                value={newPerson.aadhar}
                onChange={handleInputChange}
                placeholder="Aadhar"
                className="border p-1 rounded"
              />
            </td>
            <td>
              <input
                type="text"
                name="mobile"
                value={newPerson.mobile}
                onChange={handleInputChange}
                placeholder="Mobile"
                className="border p-1 rounded"
              />
            </td>
            <td>{newPerson.age}</td>
            <td>
              <button
                onClick={handleAddPerson}
                className="bg-blue-500 text-white px-2 py-1 rounded"
              >
                Save
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}

export default AddPersonForm;
