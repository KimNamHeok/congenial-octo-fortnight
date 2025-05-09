/**
 * 
 */
function FetchApi(baseUrl=""){
	this.fetchJson = async ()=>{
		let resp = await fetch(`${baseUrl}/form`,{
				headers : {
					"accept" : "application/json"
				}
		})
		if(resp.ok){
			return resp.json();
		}else{
			throw new Error(resp.statusText);
		}
	}
	this.fetchHtml = async (url) =>{
		let resp = await fetch(url);
		if(resp.ok){
			return resp.text();
		}else{
			throw new Error(resp.statusText);
		}
	}
}



document.addEventListener("DOMContentLoaded",async ()=>{
	let fetchApi = new FetchApi("../../mbti");
	const contentArea = document.querySelector("#content-area");
	const select = document.querySelector('select[name ="mbtiType"]');
	const form = select.form;
	
	select.addEventListener("change", (e)=>{
		e.target.form.requestSubmit();
	})
	
	form.addEventListener("submit",async (e)=>{
		e.preventDefault();
		let params = new URLSearchParams(new FormData(form));
		params.set("layout", "none");
		// 동기를 비동기로 변경하기 위해
		let url = `${form.action}?${params}`;
		let content = await fetchApi.fetchHtml(url);

//		let resp = await fetch(url);
//		if(resp.ok){
			contentArea.replaceChildren("");
//			let content = await resp.text();
			contentArea.innerHTML = content;
//		}else{
//			throw new Error(resp.statusText);
//		}
	})
	
	let mbtiProps = await fetchApi.fetchJson();
//	if(resp.ok){
//		let mbtiProps = await resp.json();
		
		// 배열 비스무리한것을 배열 형태로 만들어 주는것임!
	 	let array = Array.from(Object.entries(mbtiProps));
		let options = array.map(([n,v])=>`<option value="${n}">${v}</option>`)
							.join("\n");
							
		console.log(options);
		select.innerHTML = options;
			
		
//		for 문 에서 "in" 연산자는 ?? 반복대상이 객체이거나 배열일때
//		for 문 에서 "of" 키워드는 ?? 반복대상이 배열일때 
//		for(let p in mbtiProps){
//			console.log(p,mbtiProps[p]);
//		}
//	}else{
//		throw new Error(resp.statusText);
//	}
})