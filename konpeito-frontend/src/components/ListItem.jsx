import axios from "axios";
import { useNavigate } from "react-router-dom";

function ListItem({ application, setServerResponse }) {
  const navigate = useNavigate();

  const handleLoadPage = () => {
    navigate(`/application/${application.id}`);
  };

  const handleRemove = () => {
    axios
      .delete(`http://localhost:8080/api/applications/${application.id}`)
      .then((response) => setServerResponse(response.data))
      .catch((err) => console.error(err));
  };

  return (
    <div className="list-item">
      <div className="application-preview">
        <div className="position">
          {application.position}
        </div>
        <div className="company">
          {application.company}
        </div>
        <div className="status">
          {application.status}
        </div>
      </div>
      <div className="controls">
        <button onClick={handleLoadPage}>view details</button>
        <button onClick={handleRemove}>remove</button>
      </div>
    </div>
  );
}

export default ListItem;
