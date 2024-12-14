import { useContext, useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import '../css/Header.css';
import { LoginContext } from '../App';
import {Session} from 'react-session-api';

export default function LoginHeader() {
    const navigate = useNavigate();
    const {isLogin, setIsLogin} = useContext(LoginContext);

    function logout() {   
        navigate('/');
        setIsLogin(false);
        sessionStorage.removeItem('member');
        alert("로그아웃 되었습니다.");
    }

    return (
        <div className = "main-header" style = {{display: 'flex', justifyContent: 'space-between', padding: '10px', backgroundColor: '#f5f5f5'}}>
            <div className = "left-header" onClick = {() => navigate('/')}>
                <img src="https://img.icons8.com/ios-filled/50/000000/home.png"
                        alt="Home"
                        style={{ width: '24px', height: '24px' }}
                />
            </div>
            <div className= 'right-header'>
                {isLogin ? (
                    <div>
                        {/* <a onClick = {() => {setIsLogin(false); navigate('/');}}>마이페이지</a> */}
                        <a onClick = {() => navigate('/mypage')}>마이페이지</a>
                        <a onClick = {logout}>로그아웃</a>
                    </div>
                    ) 
                    : (
                        <div>
                            <a onClick = {() => navigate('/login')}>로그인</a>
                            <a onClick = {() => navigate('/register')}>회원가입</a>
                        </div>
                    )
                }
            </div>
        </div>
        
    )
}       