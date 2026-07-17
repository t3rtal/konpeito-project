import { useParams } from "react-router-dom";
import Navbar from "../components/NavBar";
import { useEffect, useState } from "react";
import axios from "axios";

function ApplicationDetails() {
  const { id } = useParams();
  const [application, setApplication] = useState({});

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/applications/${id}`)
      .then((response) => setApplication(response.data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <>
      <Navbar />
      <div className="page-content">
        <p>{application.company}</p>
        <p>{application.position}</p>
        <p>{application.status}</p>
        <p>{application.salary}</p>
        <p>{application.jobUrl}</p>
        <p>{application.date}</p>
      </div>
    </>
  );
}

export default ApplicationDetails;
