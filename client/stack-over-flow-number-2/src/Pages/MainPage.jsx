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
    return <div className='loading'>Loading...</div>
  }


  return <>
  {allQuestions.map(question => {
    let questionDate = question.created.replace("T", " ") 
    return <>
    <div className='questions'>
    <ul className='list' key={question.id}>
      <li className='list_item'>
        <div className='question_card'>
          <div className='question_title'>
          < Link to={`/question/${question.id}`}>
          {question.title}
          </Link>
          </div>
          <div className='counter'>
            Number of Answer {question.answerCount}
          </div>
          <div className='date'>
          {questionDate}
          </div>
        </div>
      </li>
    </ul>
    </div>
    </>
  })}
  </>
};

export default MainPage