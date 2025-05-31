package kr.or.ddit;

public class Dummy {
	// 클래스를 불러올때 올라갈애 1
	public static int a;
	
	// new 생성자를 사용하여 인스턴스로 만들때 heap메모리에 올라갈애1
	public int b;
	
	// 클래스를 method영역에 올릴때 올라갈애 2
	public static int staticA(int b) {
		a = a + b;
		return a;
	}
	
	// new 생성자를 사용하여 인스턴스로 만들때 heap메모리에 올라갈애2
	public int plusB(int b) {
		this.b += b;
		return this.b;
	}
	
	// 인스턴스로 만들어진애는 항상 여기에서 쓰인 그대로 초기값. 생성될때마다 만들어지기 때문에.
	// static 붙은 애들은 정적변수 라고 하는데 그게 왜 그러냐. 처음에 한번 딱 생성되고 값은
	// 변할수 있으나 새롭게 만들어지지않음. 
	
	// 그래서 전체적인 값이 한꺼번에 변화해야 할 때 사용.(변수)
	
	// static 메서드는 클래스명을 통해서 바로 접근하고 싶은 메서드를 만들고 싶을때 사용.
}
