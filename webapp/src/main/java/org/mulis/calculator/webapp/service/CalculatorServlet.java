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
        resp.getWriter().println("ok post");
        resp.setStatus(200);
        resp.setContentType("text/plain");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("-----------get-----------------");
        resp.getWriter().println("ok get");
        resp.setStatus(200);
        resp.setContentType("text/plain");

    }

}
