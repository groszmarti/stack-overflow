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
  {allQuestions.map(question => (
    <ul key={question.id}>
      <li>
        < Link to={`/question/${question.id}`}>
        {question.title}
        </Link>
        {question.created}
      </li>
    </ul>
  ))}
  </>
};

export default MainPage