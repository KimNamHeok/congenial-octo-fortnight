package kr.or.ddit.mbti;

/**
 * 특정한 예외 상황을 구체적으로 표현할 수 있는 커스텀 예외.
 * 커스텀 예외 정의 방법
 * 1. 예외의 특성과 처리 정책에 따라 상위 타입 결정
 *    1) Throwable : throw 할 수 있는 모든 객체의 상위
 *        - Error : 직접 처리할 수 없는 시스템 에러 상황의 표현.
 *        - Exception : 필요하다면 예외 처리 정책에 따라 직접 처리가 가능한 상황에 대한 표현.
 *        				예외를 처리한다는 것은 일종의 조건문을 형성하는 것과 비슷함.
 *    2) Exception : throw 할 수 있고, 직접 처리 가능한 예외의 상위.
 *    3) RuntimeException : unchecked exceptino 의 상위로
 *    						throw 할 수있고, 직접 처리도 가능하지만, 처리하지 않더라도 컴파일 에러는 없음.
 */
public class MbtiNotFoundException extends RuntimeException{

	public MbtiNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MbtiNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public MbtiNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MbtiNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public MbtiNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
