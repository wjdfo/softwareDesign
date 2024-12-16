import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"
import '../css/Main.css';

export default function Main() {
    const navigate = useNavigate();
    const [articleList, setArticleList] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const id = sessionStorage.getItem('member_id');

    const openModal = () => setIsModalOpen(true);
    const closeModal = () => setIsModalOpen(false);

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

    async function uploadArticle(event) {
        event.preventDefault();
        const title = event.target.title.value;
        const image = event.target.image.value;
        const content = event.target.content.value;

        try {
            const response = await fetch('http://localhost:8080/api/article/', {
                method : 'PUT',
                headers : {
                    'Content-Type' : 'application/json',
                },
                body : JSON.stringify({
                    'member_id' : id,
                    'title' : title,
                    'image' : image,
                    'text' : content,
                })
            }).then(async (response) => {
                if (response.ok) {
                    const data = await response.json();
                    console.log('upload success!');
                    alert('게시글 업로드 완료.');
                    closeModal();

                    setArticleList([{
                        article_id: data.article_id,
                        owner_id: id,
                        title: title,
                        image: image,
                        text: content,
                        time: data.time,
                    }, ...articleList]);
                }
            })
        } catch (error) {
            console.log("게시글 업로드 실패");
        }
    }

    useEffect(() => {
        getArticle();
    },[]);

    const chunnkedArticles = [];
    for (let i = 0; i < articleList.length; i += 3) {
        chunnkedArticles.push(articleList.slice(i, i+3));
    }

    return (
        <>
            <div className={`main ${isModalOpen ? 'overlay' : ''}`}>
                <div className = 'header'>
                    <h2>게시글 리스트</h2>
                    <button onClick = {openModal}>업로드</button>
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
            {isModalOpen && (
                    <div className="modal">
                        <div className="modal-content">
                            <h3>게시글 작성</h3>
                            <form onSubmit={uploadArticle}>
                                <label htmlFor="title">제목</label>
                                <input type="text" id="title" name="title" placeholder="제목 입력" />

                                <label htmlFor="image">이미지 URL</label>
                                <input type="text" id="image" name="image" placeholder="이미지 URL 입력" />

                                <label htmlFor="content">게시글</label>
                                <textarea id="content" name="content" rows="5" placeholder="게시글 내용 입력"></textarea>
                            
                                <div className="modal-buttons">
                                    <button onClick={closeModal} className="cancel">취소</button>
                                    <button className="submit" type="submit">업로드</button>
                                </div>
                            </form>
                        </div>
                    </div>
            )}
        </>
    )
}