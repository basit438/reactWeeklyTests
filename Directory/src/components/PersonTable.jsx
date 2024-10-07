import React from "react";

function PersonTable({ people, handleDeletePerson }) {
  return (
    <table className="table-auto w-full bg-white shadow-md rounded-md">
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
        {people.map((person, index) => (
          <tr key={index}>
            <td>{person.name}</td>
            <td>{person.dob}</td>
            <td>{person.aadhar}</td>
            <td>{person.mobile}</td>
            <td>{person.age}</td>
            <td>
              <button
                onClick={() => handleDeletePerson(person.aadhar)}
                className="bg-red-500 text-white px-2 py-1 rounded"
              >
                Delete
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default PersonTable;
