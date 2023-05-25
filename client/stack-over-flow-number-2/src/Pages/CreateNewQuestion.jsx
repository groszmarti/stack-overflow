import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const addQuestion = (title, description, navigate) => {

  const newQuestion = {
    title: title,
    description: description
  }

  postQuestion(newQuestion)
    .then(() => {
      navigate('/');
    });

}

const postQuestion = (newQuestion) => {
  
  return fetch('/api/questions/', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(newQuestion),
  }).then((res) => res.json());
}

const CreateNewQuestion = () => {
  const navigate = useNavigate();

  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');

  return <>
  <div className='new_card'>
    <div className='input_title'>What is your question?</div>
    <input className='input' value={title} onChange={(e) => setTitle(e.target.value)}></input><br/>
    <div className='input_title'>Description</div>
    <input className='input' value={description} onChange={(e) => setDescription(e.target.value)}></input><br/>
    <button className='buttons' type='submit' onClick={() => addQuestion(title, description, navigate)}>Add Question</button>
  </div>
  </>
}

export default CreateNewQuestion;