/**
 * 
 */
document.addEventListener("DOMContentLoaded",function(){
    // const facForm = document.querySelector('#fac-form')
    const facForm = window['fac-form'];
    facForm.addEventListener("submit", async e=>{
        console.log(e);
        e.preventDefault();
        let form = e.target;
        let url = form.action;
        // 입력키가 가지고 있는 모든값은 kwy, value로 가지고 있게 됨.
        let fd = new FormData(form);

        let queryString = new URLSearchParams(fd).toString();
        console.log("queryString : ", queryString);
        
        let resp = await fetch(`${url}?${queryString}`);
        if(resp.ok){
            // {"result" : 55}
            let {result} = await resp.json()
            resultArea.innerHTML = result; 
        }else{

        }
    });
    /* 
        글로벌변수 : window가 가지고있는 property
        window.data => data
        아이디가 가지고 있는 속성 : 그상태 그대로 글로벌변수로 반영됨. 
        그래서 아이디는 selecting하지않아도 찾을 수 있음!
        html에서는 카멜케이스를 많이 쓰지않고 facForm 대신 fac-form
        이런식으로 씀
        -때문에 변수를 찾아올수 없음
        그때엔 window['fac-form'] 이렇게 써야함함
    */
})