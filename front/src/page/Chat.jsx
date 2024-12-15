import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../css/Chat.css';

export default function Chat() {
    const id = sessionStorage.getItem("member_id");
    const [chatList, setChatList] = useState([]);
    const navigate = useNavigate();

    async function getChatList() {
        try {
            const response = await fetch(`http://localhost:8080/api/chat/member/${id}`, {
                method : 'GET'
            }).then(async (response) => {
                const data = await response.json();

                const result = data.chat_Info_list.map((chat) => ({
                    chat_id : chat.chat_id,
                    article_id : chat.article_id,
                    article_title : chat.article_title,
                }));

                setChatList(result);
            })
        } catch (error) {
            console.log("채팅 데이터 로드 실패", error);
        }
    }

    useEffect(() => {
        getChatList();
    }, []);

    return (
        <div className = 'chatList'>
            {chatList.map((chat, chatIndex) => (
                <div className = 'chat' key = {chat.chat_id} onClick = {() => navigate(`/chat/${chat.chat_id}`)}>
                    <h3>{chat.article_title}</h3>
                </div>
            ))}
        </div>
    )
}