package kr.or.ddit;

public class DummyMain {
	public static void main(String[] args) {
		// 여기에서 클래스를 내놔.했을때 컴퓨터의 뒷 작업.
		// 1.클래스를 method 메모리에 올린다. 처음에 불릴때 딱 한번.
		// 2. 클래스 내부의 static붙은 애들 method영역에 올린다.
		Dummy.a = 10;

		Dummy.staticA(11);
		System.out.println(Dummy.a);
		// new를 사용하여 힙 메모리 영역에 주소 내놔.한후 생성자를 통하여 나머지 인스턴스도 메모리에 올림.
		Dummy dummy = new Dummy();
		dummy.a = 1;
		dummy.b = 1;
		
		int check = dummy.plusB(1);
		int checkStatic = dummy.staticA(1);
		
		Dummy dummy2 = new Dummy();
		int check2 = dummy2.plusB(1);
		int checkStatic2 = dummy2.staticA(1);
		
		System.out.println(check);
		System.out.println(check2);
		System.out.println(checkStatic);
		System.out.println(checkStatic2);
		System.out.println(dummy.a);
		
		
		
	}
}
