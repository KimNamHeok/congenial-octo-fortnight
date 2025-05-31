/**
 * 
 */
document.addEventListener("DOMContentLoaded", ()=>{
//	alert(axios);
	const lprodGuSel = document.getElementById("lprodGu");
	const initVal = lprodGuSel.dataset.initVal;
	const CPATH = document.body.dataset.contextPath
	axios.get(`${CPATH}/ajax/lprod`)
		.then(resp=>{
			console.log(resp.data);
			const {lprodList} = resp.data;
			if(lprodList){
				const options = lprodList.map(({lprodGu, lprodName})=>
									`<option value="${lprodGu}">${lprodName}</option>`)
									.join("\n");
				lprodGuSel.innerHTML += options;
//				if(initVal)
					lprodGuSel.value = initVal ?? "";
			}
		})
});