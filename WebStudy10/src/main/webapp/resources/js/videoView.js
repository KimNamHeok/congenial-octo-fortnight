/**
 * 
 */
/*
	0. 페이지가 로딩이 되고나서 그안에 select태그가 완성된 이후
	1. 서버쪽으로 비동기 요청 전송 : ajax, axios, fetch, XMLHttpRequest
	2. 동적 파일 목록을 순수 데이터로 수신
	3. option 태그 생성
	4. select 채그의 innerHTML로 설정
*/
document.addEventListener("DOMContentLoaded", ()=>{
	const select = document.querySelector("[name='video']");
	select.addEventListener("change", (e)=>{
		let selected = e.target.value;
		resultArea.innerHTML = `<video src="../../03/media?${select.name}=${selected}" 
									autoplay controls></video>`;
	})
	const resolve = (resp)=>{
		if(resp.ok){
			return resp.json();
			// 데이터가 사이즈가 커질수록 변환에도 소요되는 시간이 존재함. 그즉시 언마샬링 안될수도 있음.
			// 그래서 promise가 반환됨.
			// 언마샬링해주는 객체
		}else{
			throw new Error(`응답 에러, 상태코드 : ${resp.status}`);
		}
	}
	/*const reject = err => console.error(err);*/
	const reject = console.error; //슈가심택스지향
	const resolveJson = (fileList) =>{
//		let options = fileList.map((fn)=>{`<option>${fn}</option>`}).join("\n");
/*	
	function형태가 있어야하는데 return이 안되고 있는 것임. 반환이 안됨! 람다나 화살표함수에서 중괄호 생략가능
	중괄호를 넣었을때엔 function형태를 만들려면 무조건 return을 넣어줘야함.
	중괄호 생략시 function형태로 return을 자동으로 넣어줌
*/
		let options = fileList.map((fn)=>`<option>${fn}</option>`).join("\n");
		select.innerHTML = options;
	}
	fetch("../../04/movieListData")
		.then(resolve)
		.then(resolveJson)
		.catch(reject)
		.finally(()=>console.log("비동기 요청 종료"));
	

})
