package Foundation.Exception;

public class MedicalCaseException extends RuntimeException {

    public MedicalCaseException(String message) {

        super(message);
    }

    public MedicalCaseException(String message, Throwable cause) {

        super(message, cause);
    }
}
