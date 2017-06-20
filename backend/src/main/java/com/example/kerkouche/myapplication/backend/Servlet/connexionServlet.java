package com.example.kerkouche.myapplication.backend.Servlet;

import com.example.kerkouche.myapplication.backend.Service.DataBaseService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kerkouche on 21/05/17.
 */

public class connexionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("Username");
        String password = req.getParameter("Password");

        DataBaseService dataBaseService=new DataBaseService();
        Integer b = dataBaseService.connexion(username,password);
        resp.getWriter().print(b);
    }
}
