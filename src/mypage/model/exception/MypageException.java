package mypage.model.exception;

public class MypageException extends RuntimeException {

	public MypageException() {
		super();
	}

	public MypageException(String message, Throwable cause) {
		super(message, cause);
	}

	public MypageException(String message) {
		super(message);
	}

	public MypageException(Throwable cause) {
		super(cause);
	}

	
}
