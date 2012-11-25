package org.mulis.calculator.webapp.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CalculatorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("-----------post-----------------");

        String expression = req.getParameter("expression");

        System.out.println(expression);

        CalculatorResult result = CalculatorServer.calculate(expression);

        System.out.println(result.toJSON());

        resp.getWriter().println(result.toJSON());
        resp.setContentType("application/json");
        resp.setStatus(200);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("-----------get-----------------");

        String expression = req.getParameter("expression");

        System.out.println(expression);
        resp.getWriter().println(expression);

        CalculatorResult result = CalculatorServer.calculate(expression);

        System.out.println(result.toJSON());
        resp.getWriter().println(result.toJSON());

        resp.getWriter().println("ok get");
        resp.setContentType("text/plain");
        resp.setStatus(200);

    }

}
