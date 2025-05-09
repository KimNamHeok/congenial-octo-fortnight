package kr.or.ddit.functionalinterface;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

/**
   	Optional : 조건부 실행 코드를 표현할 때 사용되고, if~else 조건문의 대체
   	Stream : 반복문의 대체ㅡ 일련의 연속성을 가진 집합 요소들에 대해 반복적인 처리를 표현할때 사용
   		-- 작업을 표현할때 FunctionalInterface 형태로 표현함.
   	FunctionalInterface ? 자바에서 일급객체(일급함수)의 표현 --> Lambda 문법으로 표현가능
   						즉 FunctionalInterface이게 있어야 Lambda문법으로 표현할 수 있다.
   	
   	일반적인 FunctionInterface(lambda) 종류 => function패키지에서 함수에 대한 것을 지원
   	1. 인자를 받고 반환값이 없음. : Consumer (Consumer 인터페이스 존재)
   	2. 인자를 받고 반환값이 있음. : function (Function 인터페이스 존재)
   	3. 인자를 받고, 여부를 확인한 후 boolean 반환(무슨여부인지 구현체에서 확인) : Predicate (Predicate 인터페이스 존재)
   	4. 인자는 없고, 새로운 객체를 반환 : Supplier(Supplier 인터페이스 존재)
   	
   	==> function이라는 패키지에 인터페이스가 없는데 람다식으로 표현해야한다.
   		그렇다면 내가 직접 만들어 줘야한다!
   	
   		일급함수는 함수형 프로그래밍언어에서 나옴! 대표적인 언어 : JavaScript
  		Java : 객체지향 언어
  		JavaScript : 함수형 프로그래밍언어
    	일급함수 : 객체가 없는데 독립적으로 존재하는 함수!
    	
   	 일급함수란?
    		1. 객체가 없이 독립적으로 존재하는 함수.
    		// 이건 자바스크립트로 작성하는 것임!
    		function name(){} == 함수
    		name() ==> 일급함수(자바에선 불가)
    		obj = {name: function(){}} == 메소드
    		obj.name() ==> 메소드
    		
    		2. 함수의 레퍼런스(주소)를 변수로 할당 받을 수 있음.
    		const fnName = () =>{}, const fnName = function(){}
    		일단 익명함수를 만들어서 메모리에 저장함! 그 후 변수에 메모리 주소를 할당함!
    		fnName()
    		
    		3. 함수를 또다른 함수의 인자로 전달할 수 있음.
    		function outer(callback){
    			...
    			callback();
    			
    		}
    		outer(fnName);
    		
    		4. 함수를 반환하는 함수가 존재 할 수 있음.
   			function fnRet(){
   				return () => {}
   			}
   			
   			const fnInner = fnRet();
 */
class LambdaAndOptionalAndStream {
	@Test
	void test3() {
		List<String> list = List.of("a1","b1","b2","c30");
		// stream : 순차적인 처리구조를 가지고 있는 메서드
		// 람다식에서는 기본형을 쓰지않음!
		String collected = list.stream()
			.map(e1->e1.substring(1))
//			.map(Integer::parseInt)
//			.filter(e1 -> e1>=30)
			.collect(Collectors.joining(","));
//			.forEach(e1->System.out.println(e1));
		System.out.println(collected);
	}
	
	@Test
	void test2() {
		Object target = null;
		if(target != null) {
			System.out.println(target.toString());
		}else {
			System.out.println("객체없음");
		}
		
		Optional.ofNullable(target)
				.map(Object::toString)
				.ifPresent(System.out::println);
	}
	//@Test라는 어노테이션이 붙으면 case 1개가 만들어짐!
	@Test
	void test() {
		FnTest oldFashion = new FnTest() {
			
			@Override
			public void print(Object arg) {
				System.out.println("엣날 방식으로 그냥 객체지향적으로 만듬 : " + arg);
				
			}
		};
		oldFashion.print("전달값");
			
		// 모든 람다는 익명 객체로 바뀜!
		FnTest newFashion = (arg) -> {System.out.println("람다로 만든 객체 : " + arg);};
		newFashion.print("전달값");
	}

}
