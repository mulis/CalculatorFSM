package calculator.exception;

public class NotComputableOperationException extends CalculationException {

    public NotComputableOperationException(int position) {
        super(String.format("Not computable operation at position %d.", position), position);
    }

}
