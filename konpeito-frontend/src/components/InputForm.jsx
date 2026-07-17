function InputField({ name, label, type }) {
  return (
    <div>
      <label>{label}</label>
      <input name={name} type={type} />
    </div>
  );
}

function InputForm({ setIsOpen, onSubmit }) {
  const handleSubmit = (e) => {
    const formData = new FormData(e.target);
    const application = Object.fromEntries(formData);
    onSubmit(application);
  };

  return (
    <div className="popup">
      <div className="popup-content">
        <button onClick={() => setIsOpen(false)}>close form</button>
        <form onSubmit={handleSubmit}>
          <InputField name={"company"} label={"company"} type={"text"} />
          <InputField name={"position"} label={"position"} type={"text"} />
          <InputField name={"status"} label={"status"} type={"text"} />
          <InputField name={"salary"} label={"salary"} type={"number"} />
          <InputField name={"jobUrl"} label={"job url"} type={"text"} />
          <InputField name={"date"} label={"date"} type={"date"} />
          <button type="submit">Submit</button>
        </form>
      </div>
    </div>
  );
}

export default InputForm;
