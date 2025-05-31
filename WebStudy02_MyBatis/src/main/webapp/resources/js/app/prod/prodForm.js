/**
 * 
 */
document.addEventListener("DOMContentLoaded", ()=>{
//	alert(axios);
	const lprodGuSel = document.getElementById("lprodGu");
	const buyerIdSel = document.getElementById("buyerId");
	
	const guInitVal = lprodGuSel.dataset.initVal;
	const idInitVal = buyerIdSel.dataset.initVal;
	
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
					lprodGuSel.value = guInitVal ?? "";
			}
		})
		
	axios.get(`${CPATH}/ajax/buyer`)
			.then(resp=>{
				console.log(resp.data);
				const {buyerList} = resp.data;
				if(buyerList){
					const options = lprodList.map(({buyerId, buyerName, lprodGu})=>
										`<option value="${lprodGu} ${buyerId}">${buyerName}</option>`)
										.join("\n");
					buyerIdSel.innerHTML += options;
	//				if(initVal)
					buyerIdGuSel.value = idInitVal ?? "";
				}
			})
			
	lprodGuSel.addEventListener("change", (e)=>{
		const selGu = e.target.value; // P101
		buyerIdSel.querySelectorAll("option[class]").forEach(op=>{
			if(op.classList.contains(selGu)){
				op.style.display = "block";
			}else{
				op.style.display = "none";
			}
		});
	});
});