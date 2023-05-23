import { Link } from "react-router-dom";

const CreateNewQuestion = () => {
  return <>
    <div>What is your question?</div>
    <input></input><br/>
    <div>Description</div>
    <input></input><br/>
    <Link to="/">
    <button>Add Question</button>
    </Link>
  </>
}

export default CreateNewQuestion;