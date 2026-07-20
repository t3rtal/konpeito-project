import axios from "axios";
import { useState } from "react";
import "./Statistics.css";

function Statistics() {
  const [statistics, setStatistics] = useState({});
  
  axios
    .get("http://localhost:8080/api/statistics")
    .then((response) => setStatistics(response.data))
    .catch((err) => console.error(err));

  return (
    <div className="statistics">
      <h4>Statistics</h4>
      <p>Total applications: {statistics.totalApplication}</p>
      <p>Offer: {statistics.offers}</p>
      <p>Interview: {statistics.interviews}</p>
      <p>Rejection: {statistics.rejections}</p>
    </div>
  );
}

export default Statistics;