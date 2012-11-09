package calculator.exception;

public class UnexpectedElementException extends CalculationException {

    public UnexpectedElementException(int position) {
        super(String.format("Unexpected element in expression at position %d.", position), position);
    }

}
