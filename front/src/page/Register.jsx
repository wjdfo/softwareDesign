import { useNavigate } from "react-router-dom"

export default function Register() {
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();

        const id = event.target.ID.value;
        const password = event.target.password.value;

        try {
            const response = fetch('http://localhost:8080/api/member/register', {
                method : "POST",
                headers : {
                    'Content-type' : 'application/json',
                },
                body : JSON.stringify({
                    "member_id" : id,
                    "password" : password
                }),
            }).then(async (response) => {
                if ( await response.ok) {
                    alert("회원 가입 성공!");
                    navigate('/login');
                }
                else {
                    alert(id+"는 이미 존재합니다.");
                }
            })
        } catch {
            alert("Sign up failed! Try it again.");
        }
    }

    return (
        <form onSubmit = { handleSubmit }>
            <input id = 'ID' type = "text" placeholder = "ID"></input>
            <input id = 'password' type = 'password' placeholder = "PASSWORD"></input>
            <button type="submit">Sign up</button>
        </form>
    )
}