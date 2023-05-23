import React from 'react'
import ReactDOM from 'react-dom/client'
import {createBrowserRouter, RouterProvider } from "react-router-dom";

import Layout from './Pages/Layout/Layout.jsx';
import MainPage from './Pages/MainPage.jsx';
import LoginPage from './Pages/LoginPage.jsx';
import CreateNewQuestion from './Pages/CreateNewQuestion';
import './index.css'

const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout />,
    children: [
      {
        path: "/",
        element: <MainPage />,
      },
      {
        path: "/login",
        element: <LoginPage />,
      },
      {
        path: "/createQuestion",
        element: <CreateNewQuestion />,
      },
    
    ],
  },
]);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
