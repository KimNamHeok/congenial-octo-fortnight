/**
 * 
 */
function InputComponent({name, type}){
	let inputElement = document.createElement("input");
	inputElement.name= name;
	inputElement.type= type;
	
	return {
		element : inputElement,
		setValue : (value) =>{
			inputElement.value = value;
		}
	}	
}

function SelectComponent({name, metaData}){
//	1. select 엘리먼트 생성
//	2. name 속성 결정
//	3. 엘리먼트 반환, 엘리먼트의 value 변경 함수
	let selectElement = document.createElement("select");
	selectElement.name = name;
	if(metaData){
		let options;
		if(Array.isArray(metaData)){
			options = metaData.map((el,idx)=>`<option value="${idx+1}">${el}</option>`)
				.join("\n");
		}else{
			options = Object.keys(metaData).map(k=>`<option value="${k}">${metaData[k]}</option>`)
				.join("\n");	
		}
		selectElement.innerHTML = options; 
	}
	return {
		element : selectElement,
		setValue : value => selectElement.value = value
	}
	
}

export {InputComponent,SelectComponent}