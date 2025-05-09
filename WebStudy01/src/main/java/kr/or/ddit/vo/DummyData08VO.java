package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Objects;

public class DummyData08VO implements Serializable{
	private String p1;
	private String p2;
	
	public String getP1() {
		return p1;
	}
	public void setP1(String p1) {
		this.p1 = p1;
	}
	public String getP2() {
		return p2;
	}
	public void setP2(String p2) {
		this.p2 = p2;
	}
	@Override
	public int hashCode() {
		return Objects.hash(p1, p2);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DummyData08VO other = (DummyData08VO) obj;
		return Objects.equals(p1, other.p1) && Objects.equals(p2, other.p2);
	}
	@Override
	public String toString() {
		return "DummyData08VO [p1=" + p1 + ", p2=" + p2 + "]";
	}
	
	
	
}
