import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"
import '../css/Main.css';

export default function Main() {
    const navigate = useNavigate();
    const [articleList, setArticleList] = useState([]);

    async function getArticle() {
        const response = await fetch('http://localhost:8080/api/article/', {
            method : 'GET'
        }).then(async (response) => {
            try {
                if (response.ok) {
                    const data = await response.json(); // ArticleList
                    
                    const result = data.article_list.map((article) => ({
                        article_id: article.id,
                        owner_id: article.owner_id,
                        title: article.title,
                        image: article.image,
                        text: article.text,
                        time: article.time,
                    }));

                    setArticleList(result);
                }
            } catch (error) {
                console.log("데이터 로딩 오류", error);
            }
        })
    }

    useEffect(() => {
        getArticle();
    },[]);

    const chunnkedArticles = [];
    for (let i = 0; i < articleList.length; i += 3) {
        chunnkedArticles.push(articleList.slice(i, i+3));
    }

    return (
        <div className = 'main'>
            <div className = 'header'>
                <h2>게시글 리스트</h2>
                <button>업로드</button>
            </div>
            <div className = "board">
                {chunnkedArticles.map((row, rowIndex) => (
                    <div className = "row" key = {rowIndex}>
                        {row.map((article, articleIndex) => (
                            <div className = "article" key = {article.article_id} onClick={() => navigate(`/article/${article.article_id}`)}>
                                <h3>{article.title}</h3>
                            </div>
                        ))}
                    </div>
                ))}
            </div>
        </div>
    )
}