package calculator.exception;

public class CalculationException extends Exception {

    private int position;

    public CalculationException(String message) {
        super(message);
    }

    public CalculationException(int position) {
        super("Error in expression at position " + position + ".");
        this.position = position;
    }


    public int getPosition() {
        return position;
    }
}
