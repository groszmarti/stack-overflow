import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom';

const MainPage = () => {

  const [allQuestions, setAllQuestions] = useState([]);

  useEffect(() => {
    fetch('/api/questions/all')
    .then(res => res.json())
    .then(data => setAllQuestions(data));

  }, []);

  if(allQuestions.length === 0){
    return <div>Loading...</div>
  }


  return <>
  {allQuestions.map(question => {
    let questionDate = question.created.replace("T", " ") 
    return <ul key={question.id}>
      <li>
        <div className='question_card'>
        <div className='question_title'>
        < Link to={`/question/${question.id}`}>
        {question.title}
        </Link>
        </div>
        <div className='question_date'>
        {questionDate}
        </div>
        </div>
      </li>
    </ul>
  })}
  </>
};

export default MainPage