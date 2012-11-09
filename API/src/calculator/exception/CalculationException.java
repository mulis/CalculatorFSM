package calculator.exception;

public class CalculationException extends Exception {

    private int position;

    public CalculationException(int position) {
        this(String.format("Error in expression at position %d.", position), position);
    }

    public CalculationException(String message, int position) {
        super(message);
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

}
