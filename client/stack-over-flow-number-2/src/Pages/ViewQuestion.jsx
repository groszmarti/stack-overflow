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

  let title, description;

  useEffect(() => {
    fetch(`/api/questions/${id}`)
      .then(res => res.json())
      .then(data => setQuestion(data))
  }, [id])

  if(question === null){
    return <div>Loading...</div>
  }

  question.map(element => {
    title = element.title;
    description = element.description;
  })
  

  return <>
  <button onClick={() => {deleteQuestion(id, navigate)}}>Delete Question</button>
  <div className="question_card">
    <div>{title}</div>
    <div>{description}</div>
  </div>
  <input></input><br/>
  <button>Add Comment</button>
  </>
}

export default ViewQuestion;