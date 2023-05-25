import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

const deleteQuestion = (id, navigate) => {
  fetch(`/api/questions/${id}`, {
    method: 'DELETE',
  })
  .then(() => {
    navigate('/');
  });
}

const addAnswer = (id, newAnswer) => {
  const pushAnswer = {
    answer: newAnswer
  }

  fetch(`/api/questions/${id}`, {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
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

const saveQuestion = (id, title, description) => {
  let warningMessage = "please fill all field"

  if(title.length === 0 || description.length === 0){
    return alert(warningMessage);
  }

  const questionUpdate = {
    title: title,
    description: description
  }

  fetch(`/api/questions/${id}`, {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(questionUpdate),
  })
  .then((res) => res.json())
  .then(() => {window.location.reload()})

}

const fetchQuestion = (id, setQuestionTitle, setQuestionDescription, setQuestionDate, setLoadPage, setQuestionAnswers) => {
  fetch(`/api/questions/${id}`)
      .then(res => res.json())
      .then(data => {
      let date = data.createdDate + " " + data.createdTime;

      setQuestionTitle(data.title);
      setQuestionDescription(data.description);
      setQuestionDate(date);

      if(data.answerCount !== 0){
        fetchAnswer(id, setQuestionAnswers);
      }
      setLoadPage(true);
    })
}

const fetchAnswer = (id, setQuestionAnswers) => {
  fetch(`/api/questions/${id}/answers`)
    .then(res => res.json())
    .then(data => setQuestionAnswers(data))
}

const ViewQuestion = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [loadPage, setLoadPage] = useState(false);
  const [newAnswer, setNewAnswer] = useState('');
  const [questionTitle, setQuestionTitle] = useState('');
  const [questionDescription, setQuestionDescription] = useState('');
  const [questionDate, setQuestionDate] = useState('');
  const [questionAnswers, setQuestionAnswers] = useState([]);

  const [isEdited, setIsEdited] = useState(false);

  useEffect(() => {
    fetchQuestion(id, setQuestionTitle, setQuestionDescription, setQuestionDate, setLoadPage, setQuestionAnswers);
  }, [id])

  if(loadPage === false){
    return <div>Loading...</div>
  }

  return <>
  
  <div className="question_card">
    {!isEdited ? <><div className='title'>{questionTitle}</div>
    <div className='description'>{questionDescription}</div>
    <div className='date'>{questionDate}</div>
    <button onClick={() => {editQuestion(id, setIsEdited)}}>Edit Question</button></> : 
    <><input value={questionTitle} onChange={(e) => {setQuestionTitle(e.target.value)}}></input><br/>
    <input value={questionDescription} onChange={(e) => {setQuestionDescription(e.target.value)}}></input><br/>
    <button onClick={() => {saveQuestion(id, questionTitle, questionDescription)}}>Save</button>
    <button onClick={() => {deleteQuestion(id, navigate)}}>Delete</button>
    </>}

  </div>

  <div className='answers'>
    <ul className='list'>
    {questionAnswers.length === 0 ? <div>No Answers</div> :
      
      questionAnswers.map(answer=> {

        return <li className='list_item' key={answer.id}>
        <div className='answer_card'>
        <div className='description'>{answer.answer}</div>
        <div className='date'>{answer.createdDate + " " + answer.createdTime}</div>
        <button className='buttons' onClick={() => {deleteAnswer(id, answer.answerId)}}>Delete Answer</button>
        </div>
        </li>
      })
    }
    </ul>
  </div>
  <div className='new_card'>
    <input className='input' value={newAnswer} onChange={(e) => setNewAnswer(e.target.value)}></input><br/>
    <button className='buttons' onClick={() => addAnswer(id, newAnswer, navigate)}>Add Answer</button>
  </div>
  </>
}

export default ViewQuestion;