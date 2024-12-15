import { useEffect, useState, useRef } from 'react';
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
    const chatListRef = useRef(null);

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

    useEffect(() => {
        // chatContent가 변경될 때 스크롤을 가장 아래로 설정
        if (chatListRef.current) {
            chatListRef.current.scrollTop = chatListRef.current.scrollHeight;
        }
    }, [chatContent]);

    async function sendChat(event) {
        event.preventDefault();
        const message = event.target.message.value;

        try {
            const response = await fetch(`http://localhost:8080/api/chat/${chat_id}`, {
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json',
                },
                body : JSON.stringify({
                    "member_id" : id,
                    "message" : message,
                }),
            }).then((response) => {
                console.log(response);
                const time = new Date();

                if (response.ok) {
                    const newMessage = {
                        sender_id: id,
                        message: message,
                        time: time.toISOString(),
                    };
        
                    // 새로운 메시지를 채팅 리스트에 추가
                    setChatContent((prevChatContent) => [...prevChatContent, newMessage]);
        
                    // 입력 필드 초기화
                    event.target.reset();
                }
            }) 
        } catch (error) {
            console.log("채팅 보내기 실패", error);
        }
    }


    return (
        <div className = 'body'>
            <div className = 'chatList' ref={chatListRef}>
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
            <form onSubmit = {sendChat}>
                <input className = 'sendChat' id = 'message' type = 'text' placeholder='메시지를 입력하세요.'></input>
                <button type = 'submit'>전송</button>
            </form>
        </div>
    )
}