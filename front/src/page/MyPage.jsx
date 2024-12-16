import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import '../css/MyPage.css';
import { LoginContext } from "../App";

export default function MyPage() {
    const id = sessionStorage.getItem('member_id');
    const password = sessionStorage.getItem('password');
    const navigate = useNavigate();
    const [articleList, setArticleList] = useState([]);
    const {isLogin, setIsLogin} = useContext(LoginContext);

    console.log(id);
    console.log(password);

    async function getMyArticleList() {
        try { const response = await fetch('http://localhost:8080/api/member/', {
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json',
                },
                body : JSON.stringify({
                    'member_id' : id,
                    'password' : password
                })
            }).then(async (response) => {
            
                if (response.ok) {
                    const data = await response.json(); // ArticleList
                    
                    console.log(data);

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
            })}catch (error) {
                console.log("데이터 로딩 오류", error);
        }
    }

    async function modifyPassword(event) {
        event.preventDefault();

        const newPassword = event.target.password.value;

        if (password === newPassword) {
            alert('새 비밀번호는 기존 비밀번호와 달라야 합니다.');
        }
        else {
            try {
                const response = await fetch('http://localhost:8080/api/member/', {
                    method : 'PATCH',
                    headers : {
                        'Content-Type' : 'application/json',
                    },
                    body : JSON.stringify({
                        'member_id' : id,
                        'password' : newPassword,
                    })
                }).then(async (response) => {
                    if (response.ok) {
                        const data = await response.json();

                        sessionStorage.removeItem('password');
                        sessionStorage.setItem('password', newPassword);
                        alert("비밀번호가 변경되었습니다.");
                        setIsLogin(false);
                        navigate('/');
                    }
                })
            } catch(error) {
                console.log("비밀번호 변경 실패");
            }
        }
    }

    useEffect(() => {
        getMyArticleList();
    }, []);

    const chunnkedArticles = [];
    for (let i = 0; i < articleList.length; i += 3) {
        chunnkedArticles.push(articleList.slice(i, i+3));
    }

    return (
        <div>
            <form onSubmit = {modifyPassword} >
                <input id = 'password' type = 'password' placeholder = "새로운 비밀번호를 입력하세요." />
                <button type = 'submit'>변경</button>
            </form>
            <h2>내 게시글</h2>
            <div className="board">
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