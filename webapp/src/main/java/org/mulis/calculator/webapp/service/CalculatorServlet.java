package org.mulis.calculator.webapp.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CalculatorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String expression = req.getParameter("expression");
        CalculatorResult result = CalculatorServer.calculate(expression);
        resp.getWriter().println(result.toJSON());
        resp.setContentType("application/json");
        resp.setStatus(200);

    }

}
