package kr.or.ddit.functionalinterface;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

/**
 * 	Optional : 조건부 실행 코드를 표현할 때 사용되고, if~else 조건문의 대체
 * 	Stream : 반복문의 대체, 일련의 연속성을 가진 집합 요소들에 대해 반복적인 처리를 표현할 때 사용.
 * 	 -- 작업을 표현할 때 FuncationalInterface 형태로 표현함
 * 	FuncationalInterface ? 자바에서 일급객체(일급함수)의 표현. --> Lambda 문법으로 표현 가능.
 * 	일반적인 FuncationalInterface(lambda) 종류
 *  1. 인자를 받고 반환값이 없음. : Consumer
 *  2. 인자를 받고 새로운 객체로 변환 : Function
 *  3. 인자를 받고, 여부를 확인한 후 boolean 반환 : Predicate
 *  4. 인자는 없고, 새로운 객체를 반환 : Supplier
 * 
 * 	일급함수란?
 * 		1. 객체없이 독립적으로 존재하는 함수
 * 		function name(){} , obj = {name:function(){}
		name()				obj.name
 * 		2. 함수의 래퍼런스(주소)를 변수로 할당받을 수 있음.
 * 		const fnName = () => {}, const fnName = function(){}
 * 		fnName()
 * 		3. 함수를 또 다른 함수의 인자로 전달할 수 있음.
 * 		function outer(callback){
 * 			...
 * 			callback();
 * 		}
 * 		outer(fnName)
 * 		4. 함수를 반환하는 함수가 존재할 수 있음.
 * 		function fnRet(){
 * 			return ()=>{}
 * 		}
 * 
 * 		const fnInner = fnRet();
 */

class LambdaAndOptionalAndStream {
	@Test
	void test3() {
		List<String> list = List.of("a1","b1","b2","c30");
		String collected = list.stream()
			.map((e1)->e1.substring(1))
//			.map(e1->Integer.parseInt(e1))
//			.filter(e1->e1>=30)
			.collect(Collectors.joining(","));
//			.forEach(e1->System.out.println(e1));
		System.out.println(collected);
	}
	
	@Test
	void test2() {
		Object target = null;
		if(target!=null) {
			System.out.println(target.toString());
		}else {
			System.out.println("객체 없음.");
		}
		
		Optional.ofNullable(target)
			.map(Object::toString)
			.ifPresent(System.out::print);
	}
	
	@Test
	void test() {
		FnTest oldFashion = new FnTest() {
			
			@Override
			public void print(Object arg) {
				System.out.println("옛날 방식으로 그냥 객체지향적으로 만듦 : "+arg);
				
			}
		};
		oldFashion.print("전달값");
		
		FnTest NewFashion = (arg)->{System.out.println("람다로 만듦 : "+arg);};
		NewFashion.print("전달값");
	}

}
