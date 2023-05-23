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
  {allQuestion.map(question => {
    return <><Link to="question/:id">
      <div key={question.id}>{`${question.title}`}</div>
    </Link><div>{question.created}</div>
    </>
  } )}
  </>
};

export default MainPage