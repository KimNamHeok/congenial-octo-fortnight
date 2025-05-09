/**
 * 
 */

const clientTime = document.querySelector("#client-time");
const serverTime = document.querySelector("#server-time");
//const startBtn = document.querySelector("#startBtn");
//const stopBtn = document.querySelector("#stopBtn");

function repeatJob(){
	let now = new Date();
		now = now.toLocaleString();
		clientTime.innerHTML = now.toString();

		fetch("/ws01/server-time")
			.then(resp=>{
				if(resp.ok){
					 return resp.text();
				}else{
					throw new Error("응답 에러", {cause : resp})
				}
			}).then(st =>{
				serverTime.innerHTML = st;
			}).catch(err =>{
				console.err(err);
			})
}

function start(){
	jobId =	setInterval(repeatJob,1000);
}
function stop(){
	clearInterval(jobId);
}
document.addEventListener("DOMContentLoaded", ()=>{
	startBtn.addEventListener("click", start)
	stopBtn.addEventListener("click", stop)
});
