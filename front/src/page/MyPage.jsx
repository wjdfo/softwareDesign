
export default function MyPage() {
    const id = sessionStorage.getItem('member_id');

    async function getMapList(id) {
        // try {
        //     const response = fetch('http://localhost:8080/article')
        // }
    }

    return (
        <div>
            <h4>{id}</h4>
            <button onClick = {console.log("hi")}>ID 변경</button>
            <div>
                {/* 게시글 리스트 보여주기 */}
            </div>
        </div>
    )
}