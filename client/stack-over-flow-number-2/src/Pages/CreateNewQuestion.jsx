import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const addQuestion = (title, description, navigate) => {

  const newQuestion = {
    'title': title,
    'description': description
  }

  postQuestion(newQuestion)
    .then(() => {
      navigate("/");
    });

    console.log("Hello")
}

const postQuestion = (newQuestion) => {
  console.log(newQuestion);
  return fetch("/api/questions/", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(newQuestion),
  }).then((res) => res.json());
}

const CreateNewQuestion = () => {
  const navigate = useNavigate();

  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");

  return <>
    <div>What is your question?</div>
    <input value={title} onChange={(e) => setTitle(e.target.value)}></input><br/>
    <div>Description</div>
    <input value={description} onChange={(e) => setDescription(e.target.value)}></input><br/>
    <button type="submit" onClick={() => addQuestion(title, description, navigate)}>Add Question</button>
  </>
}

export default CreateNewQuestion;