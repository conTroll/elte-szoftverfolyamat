package hu.szoftverfolyamat.exception;

public class MessageServiceException extends Exception {

    private static final long serialVersionUID = -7254886951566375322L;

    public MessageServiceException(final String message) {
        super(message);
    }

    public MessageServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MessageServiceException(final Throwable cause) {
        super(cause);
    }
}
