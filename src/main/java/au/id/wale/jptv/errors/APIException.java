package au.id.wale.jptv.errors;

public class APIException extends Exception {
    public APIException(String message) {
        super(message);
    }

    public APIException(String message, Exception e) {
        super(message, e);
    }
}
