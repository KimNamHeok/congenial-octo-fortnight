package kr.or.ddit.enumpkg;

public enum OperaterType {
	 PLUS('+', (l,r) -> l+r), 
	 MINUS('-',(l,r) -> l-r), 
	 MULTIPLY('*',(l,r) -> l*r),
	 DIVIDE('/',(l,r) -> l/r); 
//	final OperaterType PLUS = new OperaterType('+', (l,r) -> l+r);
//	final OperaterType MINUS = new OperaterType('-',(l,r) -> l-r);
//	final OperaterType MULTIPLY = new OperaterType('-',(l,r) -> l*r);
//	final OperaterType DIVIDE = new OperaterType('-',(l,r) -> l/r);

	private OperaterType(char sign, BiOperandOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}
	
	private char sign;
	private BiOperandOperator realOperator;
	
	public long operate (int left, int right) {
		return realOperator.operate(left, right);
	}
}
