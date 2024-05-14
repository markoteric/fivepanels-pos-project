package Exception;

public class MessengerException extends Exception {
    public MessengerException(String message) {
        super(message);
    }

    public MessengerException(String message, Throwable cause) {
        super(message, cause);
    }

}
