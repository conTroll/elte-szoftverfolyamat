package hu.szoftverfolyamat.exception;

public class UserServiceException extends Exception {

	private static final long serialVersionUID = -7764017750335673396L;

	public UserServiceException(final String message) {
        super(message);
	}

	public UserServiceException(final String message, final Throwable cause) {
        super(message, cause);
	}

	public UserServiceException(final Throwable cause) {
        super(cause);
	}
}
