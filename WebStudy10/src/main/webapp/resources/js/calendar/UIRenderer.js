/**
 * 
 */
import {InputComponent,SelectComponent} from "./InputComponents.js";
async function fetchMetaDatas(){
	let resp = await fetch("../../calendar/uiMetaDatas",{
			headers:{
				"accept": "application/json"
			}
	});
	return resp.json();
}
function createForm({months, locales, zones}){
	let form = document.createElement("form");
	let {element:yearInput, setValue:setYear} = InputComponent({name:"year", type:"number"});
	let {element:monthInput, setValue:setMonth} = 
				SelectComponent({name:"month", metaData:months});
	let {element:localeInput, setValue:setLocale}=
				SelectComponent({name:"locale", metaData:locales});
	let {element:zoneInput, setValue:setZone}= SelectComponent({name:"zone", metaData:zones});
	
	
	let btn = document.createElement("button");
	btn.type = "submit";
	btn.innerHTML = "전송";
	form.append(yearInput, monthInput, localeInput, zoneInput,btn);
	initForm({setYear, setMonth, setLocale, setZone});
	
	return form;
}

function initForm({setYear, setMonth, setLocale, setZone}){
	let today = new Date();
	setYear(today.getFullYear());
	setMonth(today.getMonth() +1);
	setLocale(navigator.language);
	setZone(Intl.DateTimeFormat().resolvedOptions().timeZone);
}

function createCalendarArea(){
	let calArea = document.createElement("div");
	calArea.id = "cal-area";
	return calArea
}
document.addEventListener("DOMContentLoaded",async ()=>{
	let metaDatas = await fetchMetaDatas();
	console.log(metaDatas);
	let form = createForm(metaDatas);
	let calArea = createCalendarArea();
	
	form.addEventListener("submit",async e =>{
		e.preventDefault();
		let formData = new FormData(form);
		let params = new URLSearchParams(formData);
		let url="../../calendar";
		let resp = await fetch(`${url}?${params}`, {
			headers :{
				"accept" : "text/html"
			}
		});
		let calHtml = await resp.text();
		calArea.innerHTML = calHtml;
	})
	form.addEventListener("change",function(){
		form.requestSubmit();
	})
	document.body.append(form, calArea);
	
	// 최조의 달력 랜더링, form의 submit 이벤트 발생이 필요함.
	//submit()이라는 이벤트를 발생시키지 않고 전송함 submit()은
	form.requestSubmit();
	// requestSubmit은 
})