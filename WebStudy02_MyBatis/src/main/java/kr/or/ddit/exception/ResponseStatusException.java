package kr.or.ddit.exception;

public class ResponseStatusException extends RuntimeException{
	private final int status;
	
	
	
	public ResponseStatusException(int status, String message) {
		super(message);
		this.status = status;
	}



	public int getStatus() {
		return status;
	}
}
