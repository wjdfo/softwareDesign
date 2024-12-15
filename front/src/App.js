import React, { useState, createContext, useContext } from 'react';
import Main from './page/Main';
import Login from './page/Login';
import Register from './page/Register';
import MyPage from './page/MyPage';
import Article from './page/Article';
import Chat from './page/Chat';
import DetailChat from './page/DetailChat';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginHeader from './header/Header';

export const LoginContext = createContext();

function App() {
  const [isLogin, setIsLogin] = useState(false);

  return (
    <LoginContext.Provider value = {{isLogin, setIsLogin}}>
      <BrowserRouter>
        <LoginHeader />
        <Routes>
          <Route path = '/login' element = {<Login/>}/>
          <Route path = '/' element = {<Main/>} />
          <Route path = '/mypage' element = {<MyPage/>}/>
          <Route path = '/register' element = {<Register/>}/>
          <Route path = "/article/:article_id" element = {<Article />} />
          <Route path = "/chat" element = {<Chat />} />
          <Route path = "/chat/:chat_id" element = {<DetailChat />} />
        </Routes>
      </BrowserRouter>
    </LoginContext.Provider>
  )
}

export default App;