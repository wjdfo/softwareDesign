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
        console.log(Id, password);
        console.log("성공했나!?");

        try {
            const response = await fetch('http://localhost:8080/api/member/login', {
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json',
                },
                body : JSON.stringify({
                    member_id : Id, password
                }),
            }).then((response) => {
                const data = response.json();
                sessionStorage.setItem("member_id", data.member_id);
                setIsLogin(true);
                alert('로그인 되었습니다.');
                navigate('/');

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
            <button type='submit'>제출</button>
        </form>
    )
}