import { Outlet, Link } from "react-router-dom";
import "./Layout.css";


const Layout = () => (
  <div className="Layout">
    <nav>
      <ul>
        <li className="grow">
          <Link to="/">Stack Overflow</Link>
        </li>
        <li>
          <Link to="/login">
            <button type="button">Login</button>
          </Link>
        </li>
      </ul>
    </nav>
    <Outlet />
  </div>
);

export default Layout;