package org.mulis.calculator.api.calculator;

public class CalculationException extends Exception {

    private int position;

    public CalculationException(String message, int position) {
        super(message);
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

}
