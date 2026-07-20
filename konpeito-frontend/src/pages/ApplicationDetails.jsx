import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import Navbar from "../components/Navbar/Navbar.jsx";
import InputForm from "../components/InputForm/InputForm.jsx";

function ApplicationDetails() {
  const { id } = useParams();
  const [application, setApplication] = useState({});
  const [isOpen, setIsOpen] = useState(false);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/applications/${id}`)
      .then((response) => setApplication(response.data))
      .catch((err) => console.error(err));
  }, []);

  const handleUpdate = (a) => {
    axios
      .put(`http://localhost:8080/api/applications/${id}`, a)
      .then((response) => setApplication(response.data))
      .catch((err) => console.error(err));
  };

  return (
    <>
      <Navbar />
      <div className="page-content">
        <div>
          <h1>{application.position}</h1>
          <h2>{application.company}</h2>
          <p>{application.status}</p>
          <p>{application.salary}</p>
          <p>{application.jobUrl}</p>
          <p>{application.date}</p>
        </div>
        <button onClick={() => setIsOpen(true)}>Update</button>
        {isOpen && <InputForm setIsOpen={setIsOpen} onSubmit={handleUpdate} />}
      </div>
    </>
  );
}

export default ApplicationDetails;
