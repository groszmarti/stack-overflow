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


const ViewQuestion = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [question, setQuestion] = useState(null);
  const [newAnswer, setNewAnswer] = useState('');

  let title, description, date;
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
    date = element.date;

    if(element.answer !== null){
      answers.push({
        answerId : element.answerId,
        answer: element.answer,
        date: element.answerDate
      });
    }
  })

  return <>
  <div className='question_card'>
    <div className='title'>{title}</div>
    <div className='description'>{description}</div>
    <div className='date'>{date}</div>
  <button className='buttons' onClick={() => {deleteQuestion(id, navigate)}}>Delete Question</button>
  </div>

  <div className='answers'>
    <ul className='list'>
    {answers.length === 0 ? <div>No Answers</div> :
      
      answers.map(answer=> {
        let answerDate = answer.date.replace('T', ' ');

        return <li className='list_item' key={answer.answerId}>
        <div className='answer_card'>
        <div className='description'>{answer.answer}</div>
        <div className='date'>{answerDate}</div>
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