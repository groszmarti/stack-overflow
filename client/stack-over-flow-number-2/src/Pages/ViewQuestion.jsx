import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

const deleteQuestion = (id, navigate) => {
  fetch(`/api/questions/${id}`, {
    method: 'DELETE',
  })
  .then(() => {
    navigate("/");
  });
}


const ViewQuestion = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [question, setQuestion] = useState(null);

  useEffect(() => {
    fetch(`/api/questions/${id}`)
      .then(res => res.json())
      .then(data => setQuestion(data))
  }, [id])

  if(question === null){
    return <div>Loading...</div>
  }

  return <>
  <button onClick={() => {deleteQuestion(id, navigate)}}>Delete Question</button>
  <div>{question.title}</div>
  <div>{question.description}</div>
  <input></input><br/>
  <button>Add Comment</button>
  </>
}

export default ViewQuestion;