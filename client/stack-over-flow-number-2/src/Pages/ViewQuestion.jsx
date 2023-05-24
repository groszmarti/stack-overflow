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

const deleteAnswer = (id, answerId) => {
  fetch(`/api/questions/${id}/${answerId}`, {
    method: 'DELETE',
  })
  .then(() => {
    window.location.reload();
  });
}

const editQuestion = (id, setIsEdited) => {
  setIsEdited(true);
}

const ViewQuestion = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [question, setQuestion] = useState(null);
  const [newAnswer, setNewAnswer] = useState('');
  const [questionTitle, setQuestionTitle] = useState('');
  const [questionDescription, setQuestionDescription] = useState('');

  const [isEdited, setIsEdited] = useState(false);

  const answers = [];

  useEffect(() => {
    fetch(`/api/questions/${id}`)
      .then(res => res.json())
      .then(data => {
        data.map(question => {
          setQuestionTitle(question.title);
          setQuestionDescription(question.description);
        })

        setQuestion(data)
      })
  }, [id])

  if(question === null){
    return <div>Loading...</div>
  }

  question.map(element => {
    if(element.answer !== null){
      answers.push({
        answerId : element.answerId,
        answer: element.answer,
        date: element.answerDate
      });
    }
  })

  return <>
  <button onClick={() => {editQuestion(id, setIsEdited)}}>Edit Question</button>
  <button onClick={() => {deleteQuestion(id, navigate)}}>Delete Question</button>
  <div className="question_card">
    {!isEdited ? <><div>{questionTitle}</div>
    <div>{questionDescription}</div></> : 
    <><input></input><br/>
    <input></input><br/>
    <button>Save</button></>}
  </div>
  <div>
    {answers.length === 0 ? <div>No Answers</div> :
      
      answers.map(answer=> {
        let answerDate = answer.date.replace("T", " ");

        return <div key={answer.answerId}>
        <div>{answer.answer}</div>
        <div>{answerDate}</div>
        <button onClick={() => {deleteAnswer(id, answer.answerId)}}>Delete Answer</button>
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