function InputField({ name, label, type }) {
  return (
    <div>
      <label>{label}</label>
      <input name={name} type={type} />
    </div>
  );
}

function InputForm({ onSubmit }) {
  const handleSubmit = (e) => {
    const formData = new FormData(e.target);
    const application = Object.fromEntries(formData);
    onSubmit(application);
  };

  return (
    <form onSubmit={handleSubmit}>
      <InputField name={"id"} label={"id"} type={"number"} />
      <InputField name={"company"} label={"company"} type={"text"} />
      <InputField name={"position"} label={"position"} type={"text"} />
      <InputField name={"salary"} label={"salary"} type={"number"} />
      <InputField name={"jobUrl"} label={"job url"} type={"text"} />
      <button type="submit">Submit</button>
    </form>
  );
}

export default InputForm;
