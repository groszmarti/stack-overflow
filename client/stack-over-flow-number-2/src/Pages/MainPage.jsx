import { useState, useEffect } from 'react'
import { Link } from "react-router-dom";

const MainPage = () => {

  const [allQuestion, setAllQuestion] = useState([]);

  useEffect(() => {
    fetch('/api/questions/all')
    .then(res => res.json())
    .then(data => setAllQuestion(data));

  }, []);

  if(allQuestion.length === 0){
    return <div>We don't have questions :'(</div>
  }

  return <>
  
  {allQuestion.map(question => (
    <ul key={question.id}>
      <li>
        <div className='question_card'>
        <div className='question_title'>
        < Link to={`/question/${question.id}`}>
        {question.title}
        </Link>
        </div>
        <div className='question_date'>
        {question.created}
        </div>
        </div>
      </li>
    </ul>
  ))}
  
  </>
};

export default MainPage