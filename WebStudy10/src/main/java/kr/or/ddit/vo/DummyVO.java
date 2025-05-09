package kr.or.ddit.vo;

public class DummyVO {
	private String prop1;
	private int prop2;
	public String getProp1() {
		return prop1;
	}
	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}
	public int getProp2() {
		return prop2;
	}
	public void setProp2(int prop2) {
		this.prop2 = prop2;
	}
	@Override
	public String toString() {
		return "DummyVO [prop1=" + prop1 + ", prop2=" + prop2 + "]";
	}
	
	
}
