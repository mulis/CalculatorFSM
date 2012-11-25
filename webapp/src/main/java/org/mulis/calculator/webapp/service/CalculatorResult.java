package org.mulis.calculator.webapp.service;

import org.mulis.calculator.api.calculator.CalculationException;

import java.math.BigDecimal;

public class CalculatorResult {

    private String result = "";
    private String error = "";
    private int position = -1;

    public CalculatorResult(BigDecimal result) {
        this.result = result.toString();
    }

    public CalculatorResult(CalculationException exception) {
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
        builder.append("result:").append("\"").append(result).append("\"").append(",");
        builder.append("error:").append("\"").append(error).append("\"").append(",");
        builder.append("position:").append("\"").append(position).append("\"");
        builder.append("}");
        return builder.toString();
    }

}
