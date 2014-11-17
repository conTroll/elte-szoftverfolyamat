package hu.szoftverfolyamat.exception;

public class UserServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7764017750335673396L;

	private String message;
	private Throwable throwable;

	public UserServiceException() {

	}

	public UserServiceException(String message) {
		this.message = message;
	}

	public UserServiceException(String message, Throwable throwable) {
		this.message = message;
		this.throwable = throwable;
	}

	public UserServiceException(Throwable throwable) {
		this.throwable = throwable;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	public Throwable getThrowable() {
		return this.throwable;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
}
