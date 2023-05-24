import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";


const ViewQuestion = () => {
  const { id } = useParams();
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
  <div>{question.title}</div>
  <div>{question.description}</div>
  <input></input><br/>
  <button>Add Comment</button>
  </>
}

export default ViewQuestion;