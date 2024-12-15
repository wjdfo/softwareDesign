import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import '../css/DetailChat.css';

function formatDateTime(dateString) {
    const date = new Date(dateString);
    const options = {
        hour: '2-digit',
        minute: '2-digit',
        hour12: false, // 24시간 형식
    };
    
    return date.toLocaleString('en-GB', options).replace(/,/, '');
}

export default function DetailChat() {
    const id = sessionStorage.getItem("member_id");
    const { chat_id } = useParams();
    const [chatContent, setChatContent] = useState([]);
    const navigate = useNavigate();

    // localhost:8080/api/chat/{chat_id}
    
    console.log(id);
    console.log(chat_id);

    async function getChatContent() {
        try {
            const response = await fetch(`http://localhost:8080/api/chat/${chat_id}`, {
                method : 'GET',
            }).then(async (response) => {
                const data = await response.json();
                
                console.log(data);

                const result = data.chat_list.map((chat) => ({
                    sender_id : chat.sender_id,
                    message : chat.message,
                    time : chat.time,
                }));

                setChatContent(result);
            })
        } catch (error) {
            console.log("채팅 데이터 로드 실패", error);
        }
    }

    useEffect(() => {
        getChatContent();
    }, []);

    return (
        <div className = 'body'>
            <div className = 'chatList'>
                {chatContent.map((chat, chatIndex) => (
                    id === chat.sender_id
                    ? (
                        // 내가 보냈을 때
                        <div className = 'mychat'>
                            <h3>나</h3>
                            <div className = 'mychat-component'>
                                <h4>{formatDateTime(chat.time)}</h4>
                                <h3>{chat.message}</h3>
                            </div>
                        </div>
                    )
                    : (
                        // 다른 사람이 보냈을 때
                        <div className = 'otherchat'>
                            <h3>{chat.sender_id}</h3>
                            <div className = 'otherchat-component'>
                                <h4>{formatDateTime(chat.time)}</h4>
                                <h3>{chat.message}</h3>
                            </div>
                        </div>
                    )
                ))}
            </div>
        </div>
    )
}