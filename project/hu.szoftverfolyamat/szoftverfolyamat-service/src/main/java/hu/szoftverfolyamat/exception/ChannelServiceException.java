package hu.szoftverfolyamat.exception;

public class ChannelServiceException extends Exception {

	private static final long serialVersionUID = -6608609709238531345L;

	public ChannelServiceException() {
	}

	public ChannelServiceException(String message) {
		super(message);
	}

	public ChannelServiceException(Throwable cause) {
		super(cause);
	}

	public ChannelServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ChannelServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
