import React, { useState } from "react";
import { buyFund } from "../services/ApiService";

const TransactionModal = ({ onClose, onSuccess }) => {
  const [fundId, setFundId] = useState("");
  const [amount, setAmount] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async () => {
    if (!fundId || !amount) {
      return alert("All fields are required");
    }

    if (parseFloat(amount) <= 0) {
      return alert("Amount must be greater than 0");
    }

    try {
      setLoading(true);

      await buyFund({
        fundId: Number(fundId),
        amount: amount.toString(), 
      });

      onSuccess();
      onClose();
    } catch (err) {
      alert(err.response?.data || "Transaction failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-30 flex justify-center items-center">

      <div className="bg-white p-6 rounded-2xl w-96 space-y-4">

        <h2 className="text-xl font-semibold">Buy Fund</h2>

        <input
          type="number"
          placeholder="Fund ID"
          value={fundId}
          onChange={(e) => setFundId(e.target.value)}
          className="w-full border p-2 rounded"
        />

        <input
          type="number"
          placeholder="Amount"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
          className="w-full border p-2 rounded"
        />

        <div className="flex justify-end gap-2">
          <button
            onClick={onClose}
            className="px-4 py-2 border rounded"
          >
            Cancel
          </button>

          <button
            onClick={handleSubmit}
            disabled={loading}
            className="bg-blue-600 text-white px-4 py-2 rounded"
          >
            {loading ? "Processing..." : "Buy"}
          </button>
        </div>
      </div>
    </div>
  );
};

export default TransactionModal;