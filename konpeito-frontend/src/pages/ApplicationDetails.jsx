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
      <p>{application.id}</p>
      <p>{application.company}</p>
      <p>{application.position}</p>
    </>
  );
}

export default ApplicationDetails;
