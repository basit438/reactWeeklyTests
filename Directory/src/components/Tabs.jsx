import React from "react";

function Tabs({ activeTab, setActiveTab }) {
  return (
    <div className="flex justify-center mb-4">
      <button
        className={`px-4 py-2 ${
          activeTab === "add" ? "bg-blue-500 text-white" : "bg-gray-300"
        }`}
        onClick={() => setActiveTab("add")}
      >
        Add New Person
      </button>
      <button
        className={`px-4 py-2 ml-2 ${
          activeTab === "retrieve" ? "bg-blue-500 text-white" : "bg-gray-300"
        }`}
        onClick={() => setActiveTab("retrieve")}
      >
        Retrieve Information
      </button>
    </div>
  );
}

export default Tabs;
