import {useNavigate} from 'react-router-dom';
import { LoginContext } from '../App';
import React, {useContext} from 'react';

export default function Login() {
    const {isLogin, setIsLogin} = useContext(LoginContext);
    const navigate = useNavigate();

    async function handleSubmit(event) {
        event.preventDefault();

        const Id = event.target.Id.value;
        const password = event.target.password.value;

        try {
            const response = await fetch('http://localhost:8080/api/member/login', {
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json',
                },
                body : JSON.stringify({
                    "member_id" : Id,
                    "password" : password
                }),
            }).then(async (response) => {
                if (response.ok) {
                    const data = await response.json();
                    console.log(data);
                    console.log(data.member_id);
                    sessionStorage.setItem("member_id", data.member_id);
                    setIsLogin(true);
                    alert('로그인 되었습니다.');
                    navigate('/');
                }
                else {
                    alert('로그인 실패');
                }

            })
        } catch {
            console.log("login failed!");
            alert("Sign in failed ! Try it again.");
        }

    }

    return (
        <form onSubmit={handleSubmit}>
            <input id = 'Id' type = 'text' placeholder = 'ID'></input>
            <input id = 'password' type = 'password'></input>
            <button type='submit'>Sign in</button>
        </form>
    )
}