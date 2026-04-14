import React, { useEffect, useState } from "react";
import { getPortfolio, sellFund } from "../services/ApiService";
import TransactionModal from "../components/TransactionModal";
import { TrendingUp, Wallet, ArrowRight } from "lucide-react";

const Dashboard = () => {
  const [portfolio, setPortfolio] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showModal, setShowModal] = useState(false);

  const fetchPortfolio = async () => {
    try {
      const res = await getPortfolio();
      setPortfolio(res.data);
    } catch (err) {
      console.error("Error fetching portfolio", err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchPortfolio();
  }, []);

  const totalInvested = portfolio.reduce(
    (sum, p) => sum + parseFloat(p.investedAmount),
    0
  );

  const currentValue = portfolio.reduce(
    (sum, p) => sum + parseFloat(p.units) * parseFloat(p.fund.nav),
    0
  );

  const handleSell = async (fundId, maxUnits) => {
    const units = prompt("Enter units to sell:");

    if (!units || parseFloat(units) <= 0) {
      return alert("Invalid units");
    }

    if (parseFloat(units) > parseFloat(maxUnits)) {
      return alert("Cannot sell more than owned units");
    }

    try {
      await sellFund({
        fundId,
        units: units.toString(), 
      });

      fetchPortfolio();
    } catch (err) {
      alert(err.response?.data || "Sell failed");
    }
  };

  if (loading) return <div className="p-6">Loading...</div>;

  return (
    <div className="p-6 space-y-6">

      <div className="grid grid-cols-2 gap-4">
        <div className="bg-white shadow rounded-2xl p-4 flex items-center gap-4">
          <Wallet />
          <div>
            <p className="text-gray-500">Total Invested</p>
            <p className="text-xl font-bold">₹ {totalInvested.toFixed(2)}</p>
          </div>
        </div>

        <div className="bg-white shadow rounded-2xl p-4 flex items-center gap-4">
          <TrendingUp />
          <div>
            <p className="text-gray-500">Current Value</p>
            <p className="text-xl font-bold">₹ {currentValue.toFixed(2)}</p>
          </div>
        </div>
      </div>

      <button
        onClick={() => setShowModal(true)}
        className="bg-blue-600 text-white px-4 py-2 rounded-xl"
      >
        Buy Fund
      </button>

      <div className="bg-white shadow rounded-2xl p-4">
        <table className="w-full text-left">
          <thead>
            <tr className="text-gray-500">
              <th>Fund</th>
              <th>Units</th>
              <th>NAV</th>
              <th>Action</th>
            </tr>
          </thead>

          <tbody>
            {portfolio.map((p) => (
              <tr key={p.id} className="border-t">
                <td>{p.fund.name}</td>
                <td>{p.units}</td>
                <td>₹ {p.fund.nav}</td>
                <td>
                  <button
                    onClick={() =>
                      handleSell(p.fund.id, p.units)
                    }
                    className="text-red-500 flex items-center gap-1"
                  >
                    Sell <ArrowRight size={16} />
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {showModal && (
        <TransactionModal
          onClose={() => setShowModal(false)}
          onSuccess={fetchPortfolio}
        />
      )}
    </div>
  );
};

export default Dashboard;