package Domain.Exception;

public class MessengerException extends RuntimeException {

    public MessengerException(String message) {

        super(message);
    }

    public MessengerException(String message, Throwable cause) {

        super(message, cause);
    }
}
