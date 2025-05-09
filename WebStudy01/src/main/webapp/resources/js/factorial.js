/**
 * 
 */
document.addEventListener("DOMContentLoaded", ()=>{
    // const facForm = document.querySelector('#fac-form')
    const facForm = window['fac-form'];   // 연관배열? 위에꺼랑 같은 뜻
    facForm.addEventListener("submit", async (e)=>{
        e.preventDefault();  // 이벤트 취소시킴 페이지 전환 막기위해 but 값은 보내줘야 함
        let form = e.target;
        let url = form.action;
        let fd = new FormData(form);
        let queryString = new URLSearchParams(fd).toString();
        console.log("queryString : ", queryString);
        // ../../05
        let resp = await fetch(`${url}?${queryString}`);
        if(resp.ok){
            // {"result":55} 최종적으로 이렇게   55는 연산의 결과
            let {result} = await resp.json();
            resultArea.innerHTML = result;
		}else{
			
        }
    });
});