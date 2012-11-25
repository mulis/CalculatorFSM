package org.mulis.calculator.webapp.service;

import org.mulis.calculator.api.calculator.CalculationException;
import org.mulis.calculator.service.calculator.Calculator;

public class CalculatorServer {

    public static CalculatorResult calculate(String expression) {

        CalculatorResult result;

        try {

            Calculator calculator = new Calculator();

            result = new CalculatorResult(calculator.calculate(expression));

        } catch (CalculationException exception) {

            result = new CalculatorResult(exception);

        }

        return result;

    }

}
