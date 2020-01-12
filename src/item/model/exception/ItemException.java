package item.model.exception;

public class ItemException extends RuntimeException {

	public ItemException() {
		super();
	}

	public ItemException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemException(String message) {
		super(message);
	}

	public ItemException(Throwable cause) {
		super(cause);
	}

	
}
