/**
 * 
 */
/*
	0. 페이지가 로딩이 되고나서 그안에 select태그가 완성된 이후
	1. 서버쪽으로 비동기 요청 전송
	2. 동적 파일 목록을 순수 데이터로 수신
	3. option 태그 생성
	4. select 채그의 innerHTML로 설정
*/
document.addEventListener("DOMContentLoaded", ()=>{
	let select = document.querySelector("[name='video']");
/*	fetch({
		url : '/ws01/04/movieListData'	
	})
	.then(data => {
		let code = "";
		data.forEach(v=>{
			code += `<option>${v}</option>`
		})
		select.appendChild(code);
	})
	.catch(err =>{
		alert(err.statusText);
	})*/
	
	function fck(url){
		return new Promise((res, rej)=>{
			let xhr = new XMLHttpRequest();
			xhr.open("get",url, true);
			xhr.onload = () =>{
				res(xhr.responseText);
			}
			xhr.send();
		})
	}

	fck("/ws01/04/movieListData")
		.then(res =>{
			return JSON.parse(res)
		})
		.then(function(datas){
			console.log(datas);
			let code = "";
			for(let i=0; i<datas.length;i++){
				code += `<option>${datas[i]}</option>`
			}
			select.appendChild(code);
		})
		.catch(err =>{
			alert(err.statusText);
		})
})
