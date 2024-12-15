import { useParams } from "react-router-dom";
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

    async function getDetailArticle(article_id) {
        try {
            const response = await fetch(`http://localhost:8080/api/article/${article_id}`, {
                method : 'GET'
            }).then(async (response) => {
                if (response.ok) {
                    const data = await response.json();
                    console.log(data);
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

    if (!article) return <div>Loading...</div>;

    return (
        <div className = 'articleDetail'>
            <div className = 'articleHeader'>
                <div className = 'articleTitle'>
                    <h2>{article.title}</h2>
                </div>
                <h4>{formatDateTime(article.time)}</h4>
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