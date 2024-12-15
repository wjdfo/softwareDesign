import { useNavigate, useParams } from "react-router-dom";
import {useEffect, useState} from 'react';
import '../css/Article.css';

function formatDateTime(dateString) {
    const date = new Date(dateString);
    const options = {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
        hour12: false, // 24시간 형식
    };
    
    return date.toLocaleString('en-GB', options).replace(/,/, '');
}

export default function Article() {
    const { article_id } = useParams();
    const [article, setArticle] = useState({});
    const navigate = useNavigate();
    const id = sessionStorage.getItem("member_id");

    async function getDetailArticle(article_id) {
        try {
            const response = await fetch(`http://localhost:8080/api/article/${article_id}`, {
                method : 'GET'
            }).then(async (response) => {
                if (response.ok) {
                    const data = await response.json();
                    setArticle(data);
                }
            });
        } catch(error) {
            console.log("데이터 로드 오류", error);
        }
    }

    useEffect(() => {
        getDetailArticle(article_id);
    },[])

    async function makeChat(event) {
        try {
            const response = fetch(`http://localhost:8080/api/chat/article/${article_id}`, {
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json',
                },
                body : JSON.stringify({
                    'member_id' : id,
                }),
            }).then(async (response) => {
                const data = await response.json();

                console.log("Chat is made successfully with ", data.owner_id);
                navigate('/chat/' + data.chat_id);
            })
        } catch (error) {
            console.log("make chat error : ", error);
        }
    }

    // async function modifyArticle(event) {
    //     event.preventDefault();
    // }

    if (!article) return <div>Loading...</div>;

    return (
        <div className = 'articleDetail'>
            <div className = 'articleHeader'>
                <div className = 'articleTitle'>
                    <h2>{article.title}</h2>
                </div>
                <div className = "subHeader">
                    <h4>{formatDateTime(article.time)}</h4>
                    {
                        article.owner_id === id ? (<div className = "myOption">
                                                        <button type = 'button' id = "modify">수정</button>
                                                        <button type = 'button' id = 'delete'>삭제</button>
                                                    </div>)
                                                : (
                                                    <button type = 'button' onClick= {() => {makeChat();}}>Chat</button> 
                                                    
                                                )
                    }
                </div>
            </div>
            <div className = 'articleImage'>
                <h3>{article.image}</h3>
            </div>
            <div className = 'articleContent'>
                <h4>{article.text}</h4>
            </div>
        </div>
    );
}