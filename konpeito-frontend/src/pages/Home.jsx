import { useEffect, useState } from "react";
import axios from "axios";
import ListItem from "../components/ListItem";
import InputForm from "../components/InputForm";
import Navbar from "../components/NavBar";

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
      <h1>Welcome back!</h1>
      <h2>Here is your list of applications</h2>
      <button onClick={() => setIsOpen(true)}>add application</button>
      {isOpen && (
        <div className="popup">
          <div className="popup-content">
            <button onClick={() => setIsOpen(false)}>close form</button>
            <InputForm onSubmit={handleAddApplication} />
          </div>
        </div>
      )}
      <ul>{listItems}</ul>
    </>
  );
}

export default Home;
