/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.kanarski.booking.controllers;

import by.kanarski.booking.requestHandler.RequestHandler;
import by.kanarski.booking.utils.DataSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author khudnitsky
 * @version 1.0
 */
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestHandler.processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestHandler.processRequest(request, response);
    }

    @Override
    public void destroy() {
        DataSource.getInstance().destroy();
    }
}
