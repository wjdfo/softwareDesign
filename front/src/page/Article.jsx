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
    const [isModalOpen, setIsModalOpen] = useState(false);
    
    const openModal = () => setIsModalOpen(true);
    const closeModal = () => setIsModalOpen(false);

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

    async function modifyArticle(event) {
        event.preventDefault();

        const modifiedTitle = event.target.title.value;
        const modifiedImage = event.target.image.value;
        const modifiedText = event.target.content.value;

        try {
            const response = await fetch(`http://localhost:8080/api/article/${article_id}`, {
                method : 'PATCH',
                headers : {
                    'Content-Type' : 'application/json'
                },
                body : JSON.stringify({
                    "member_id" : id,
                    "title" : modifiedTitle,
                    "image" : modifiedImage,
                    "text" : modifiedText
                })
            }).then(async (response) => {
                if (await response.ok){
                    alert('게시글 수정 완료');
                    setArticle({
                        'id' : article.id,
                        'owner_id' : article.owner_id,
                        'title' : modifiedTitle,
                        'image' : modifiedImage,
                        'text' : modifiedText,
                        'time' : article.time
                    })
                    closeModal();
                }
            })
        } catch (error) {
            console.log("게시글 수정 실패 ", error);
        }
    }

    async function deleteArticle(event) {
        event.preventDefault();

        try {
            const response = await fetch(`http://localhost:8080/api/article/${article_id}`, {
                method : "DELETE",
                headers : {
                    'Content-Type' : 'application/json'
                },
                body : JSON.stringify({
                    "member_id" : id,
                })
            }).then(async (response) => {
                alert('게시글이 삭제되었습니다.');
                navigate('/');
            })
        } catch(error) {
            console.log("게시글 삭제 실패 ", error);
        }

    }

    if (!article) return <div>Loading...</div>;

    return (
        <>
            <div className = {`articleDetail ${isModalOpen ? 'overlay' : ''}`}>
                <div className = 'articleHeader'>
                    <div className = 'articleTitle'>
                        <h2>{article.title}</h2>
                    </div>
                    <div className = "subHeader">
                        <h4>{formatDateTime(article.time)}</h4>
                        {
                            article.owner_id === id ? (<div className = "myOption">
                                                            <button type = 'button' id = "modify" onClick = {openModal}>수정</button>
                                                            <button type = 'button' id = 'delete' onClick = {deleteArticle}>삭제</button>
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
            {isModalOpen && (
                    <div className="modal">
                        <div className="modal-content">
                            <h3>게시글 수정</h3>
                            <form onSubmit={modifyArticle}>
                                <label htmlFor="title">제목</label>
                                <input type="text" id="title" name="title" defaultValue = {article.title} placeholder="제목 입력" />

                                <label htmlFor="image">이미지 URL</label>
                                <input type="text" id="image" name="image" defaultValue = {article.image} placeholder="이미지 URL 입력" />

                                <label htmlFor="content">게시글</label>
                                <textarea id="content" name="content" rows="5" defaultValue = {article.text} placeholder="게시글 내용 입력"></textarea>
                            
                                <div className="modal-buttons">
                                    <button onClick={closeModal} className="cancel">취소</button>
                                    <button className="submit" type="submit">수정 완료</button>
                                </div>
                            </form>
                        </div>
                    </div>
            )}
        </>
    );
}