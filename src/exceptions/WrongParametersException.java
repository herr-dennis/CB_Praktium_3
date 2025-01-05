package exceptions;

public class WrongParametersException extends Exception {

    public WrongParametersException() {
        super(WrongParametersException.class.getSimpleName());
    }
    public WrongParametersException(String message) {
        super(message);
    }

}
