import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"

export default function Main() {
    const navigate = useNavigate();
    const [articleList, setArticleList] = useState([]);
    const result = [];

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

    console.log(articleList.length);
    for (let i = 0; i< articleList.length; i++) {
        console.log(
            articleList[i].article_id, articleList[i].title
        );
    }

    const chunnkedArticles = [];
    for (let i = 0; i < articleList.length; i += 3) {
        chunnkedArticles.push(articleList.slice(i, i+3));
    }


    return (
        <div className = "board">
            {chunnkedArticles.map((row, rowIndex) => (
                <div className = "row" key = {rowIndex}>
                    {row.map((article, articleIndex) => (
                        <div className = "article" key = {articleIndex}>
                            <h3>{article.title}</h3>
                        </div>
                    ))}
                </div>
            ))}
        </div>
    )
}