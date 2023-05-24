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

const addAnswer = (id, newAnswer, navigate) => {
  const pushAnswer = {
    answer: newAnswer
  }

  fetch(`/api/questions/${id}`, {
  method: "POST",
  headers: {
    "Content-Type": "application/json",
  },
  body: JSON.stringify(pushAnswer),
  }).then(res => res.json()).then(() => window.location.reload())
}


const ViewQuestion = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [question, setQuestion] = useState(null);
  const [newAnswer, setNewAnswer] = useState('');

  let title, description;
  const answers = [];

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

    if(element.answer !== null){
      answers.push({
        answer: element.answer,
        date: element.answerDate
      });
    }
  })

  return <>
  <button onClick={() => {deleteQuestion(id, navigate)}}>Delete Question</button>
  <div className="question_card">
    <div>{title}</div>
    <div>{description}</div>
  </div>
  <div>
    {answers.length === 0 ? <div>No Answers</div> :
      
      answers.map((answer, index) => {
        let answerDate = answer.date.replace("T", " ");

        return <div key={index}>
        <div>{answer.answer}</div>
        <div>{answerDate}</div>
        </div>
      }
        
      )
      
    }
  </div>
  <input value={newAnswer} onChange={(e) => setNewAnswer(e.target.value)}></input><br/>
  <button onClick={() => addAnswer(id, newAnswer, navigate)}>Add Comment</button>
  </>
}

export default ViewQuestion;