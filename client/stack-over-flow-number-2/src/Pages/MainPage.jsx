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
    return <div>Loading...</div>
  }

  return <>
  
  {allQuestion.map(question => (
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