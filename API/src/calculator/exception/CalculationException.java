package calculator.exception;

public class CalculationException extends Exception {

    public CalculationException(String message) {
        super(message);
    }

    public CalculationException(int position) {
        super("Error in expression at position " + position + ".");
    }

}
