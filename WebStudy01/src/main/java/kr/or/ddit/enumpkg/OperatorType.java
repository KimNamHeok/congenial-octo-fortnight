package kr.or.ddit.enumpkg;

public enum OperatorType {
	PLUS, MINUS;
//	final OperatorType PLUS = new OperatorType('+', (l,r)->l+r);
//	final OperatorType MINUS = new OperatorType('-', (l,r)->l-r);

	private OperatorType(char sign, BiOperandOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}
	private char sign;
	private BiOperandOperator realOperator;
	
	public long operate(int left, int right) {
		return realOperator.operate(left, right);
	}
	
}
