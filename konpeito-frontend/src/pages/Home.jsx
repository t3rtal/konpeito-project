import { useEffect, useState } from "react";
import axios from "axios";
import ListItem from "../components/ListItem/ListItem.jsx";
import InputForm from "../components/InputForm/InputForm.jsx";
import Navbar from "../components/Navbar/Navbar.jsx";
import Statistics from "../components/Statistics/Statistics.jsx";

function Home() {
  const [applications, setApplications] = useState([]);
  const [serverResponse, setServerResponse] = useState("");
  const [isOpen, setIsOpen] = useState(false);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/applications")
      .then((response) => setApplications(response.data))
      .catch((e) => console.error(e));
  }, [serverResponse]);

  const listItems = applications.map((application, index) => {
    return (
      <li key={index}>
        <ListItem
          application={application}
          setServerResponse={setServerResponse}
        />
      </li>
    );
  });

  const handleAddApplication = (application) => {
    axios
      .post("http://localhost:8080/api/applications", application)
      .then((response) => setServerResponse(response.data))
      .catch((err) => console.error(err));
  };

  return (
    <>
      <Navbar />
      <div className="page-content">
        <div className="overview">
          <div className="greeting">
            <h1>Hi, Tertal!</h1>
            <h2>Here is your list of applications</h2>
          </div>
          <Statistics />
        </div>
        <button className="add-application" onClick={() => setIsOpen(true)}>add application</button>
        {isOpen && (
          <InputForm setIsOpen={setIsOpen} onSubmit={handleAddApplication} />
        )}
        <ul>{listItems}</ul>
      </div>
    </>
  );
}

export default Home;
