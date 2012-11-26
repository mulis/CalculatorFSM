package org.mulis.calculator.webapp.service;

import org.mulis.calculator.api.calculator.CalculationException;

import java.math.BigDecimal;

public class CalculatorResult {

    private String expression = "";
    private String result = "";
    private String error = "";
    private int position = -1;

    public CalculatorResult(String expression, BigDecimal result) {
        this.expression = expression;
        this.result = result.toString();
    }

    public CalculatorResult(String expression, CalculationException exception) {
        this.expression = expression;
        this.error = exception.getMessage();
        this.position = exception.getPosition();
    }

    public String getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    public int getPosition() {
        return position;
    }

    public String toJSON() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("\"expression\":\"").append(expression).append("\",");
        builder.append("\"result\":\"").append(result).append("\",");
        builder.append("\"error\":\"").append(error).append("\",");
        builder.append("\"position\":\"").append(position).append("\"");
        builder.append("}");
        return builder.toString();
    }

}
