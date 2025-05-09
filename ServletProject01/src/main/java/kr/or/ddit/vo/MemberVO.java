package kr.or.ddit.vo;

public class MemberVO {
	private int memNo;
	private String memId;
	private String memPass;
	private String memName;

	
	public String getMemPass() {
		return memPass;
	}

	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public MemberVO() {}
	
	public MemberVO(int no, String id) {
		this.memNo = no;
		this.memId = id;
	}
	

	

	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	
	@Override
	public String toString() {

		return "MemberVO [no : "+memNo+", id : "+memId+"]";
	}
	
}
