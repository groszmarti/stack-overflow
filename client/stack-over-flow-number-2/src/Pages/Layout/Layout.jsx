import { Outlet, Link } from "react-router-dom";
import "./Layout.css";


const Layout = () => (
  <div className="Layout">
    <nav className="header">
      <ul>
        <li className='site_title'>
          <Link to="/" className="grow">Stack Overflow</Link>
        </li>
        <li className='link_button'>
          <Link to="/createQuestion">
            <button className="button" type="button">New Question</button>
          </Link>
        </li>
        <li className='link_button'>
          <Link to="/login">
            <button className="button" type="button">Login</button>
          </Link>
        </li>
      </ul>
    </nav>
    <Outlet />
  </div>
);

export default Layout;