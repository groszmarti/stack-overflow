import { useState, useEffect } from 'react'

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
  {allQuestion.map(question => {console.log(question)
    return <div>{`${question.title} - ${question.created}`}</div>
  } )}
  </>
};

export default MainPage