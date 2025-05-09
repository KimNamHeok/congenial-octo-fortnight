/**
 * 
 */
let now = new Date();
now = now.toLocaleString();

const clientTime = document.querySelector("#client-time");
const serverTime = document.querySelector("#server-time");
clientTime.innerHTML = now.toString();

fetch("/ws01/server-time")
	.then(resp=>{
		console.log(resp);
		resp.text().then(
			serverTime.innerHTML = resp
		)
	
	})
	.catch(xhr=>{
		alert(xhr.statusText);
	})