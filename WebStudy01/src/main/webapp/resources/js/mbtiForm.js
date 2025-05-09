/**
 * 
 */
function FetchApi(baseUrl=""){
	this.fetchJson = async ()=>{
		let resp = await fetch(`${baseUrl}/form`, {
			headers:{
				"accept":"application/json"
			}
		});
		if(resp.ok){
			return resp.json();
		}else{
			throw new Error(resp.statusText);
		}
	}
	this.fetchHtml = async (url)=>{
		let resp = await fetch(url);
		if(resp.ok){
			return resp.text();
		}else{
			throw new Error(resp.statusText);
		}
	}
}

document.addEventListener("DOMContentLoaded", async ()=>{
	let fetchApi = new FetchApi("../../mbti");
	const contentArea = document.querySelector("#content-area");
	const select = document.querySelector(`[name="mbtiType"]`);
	const form = select.form;
	select.addEventListener("change", (e)=>{
		e.target.form.requestSubmit();
	});
	form.addEventListener("submit", async (e)=>{
		e.preventDefault();
//		동기를 비동기로 변경하기 위해.
		let params = new URLSearchParams(new FormData(form));
		params.set("layout", "none");
		let url = `${form.action}?${params}`;
		let content = await fetchApi.fetchHtml(url);
		contentArea.replaceChildren("");
		contentArea.innerHTML = content;
	});
	
	let mbitProps = await fetchApi.fetchJson();
	let array = Array.from(Object.entries(mbitProps))
	let options = array.map(([n,v])=>`<option value="${n}">${v}</option>`).join("\n");
	console.log(options);
	select.innerHTML = options;

/*		for 문에서 "in" 연산자는 ?? 반복의 대상이 객체이거나 배열일 때
		for 문에서 "of" 키워드는 ?? 반복의 대상이 주로 배열일 때
		
		for(let p in mbtiProps){
//			ex) mbtiProps.entj
//			ex) mbtiProps[p]
			console.log(p, mbtiProps[p]);
		}*/

});



