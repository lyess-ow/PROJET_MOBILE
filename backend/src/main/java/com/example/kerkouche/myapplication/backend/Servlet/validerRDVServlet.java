package com.example.kerkouche.myapplication.backend.Servlet;

import com.example.kerkouche.myapplication.backend.Model.RendezVous;
import com.example.kerkouche.myapplication.backend.Service.DataBaseService;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kerkouche on 14/05/17.
 */

public class validerRDVServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id_rdv = Integer.parseInt(req.getParameter("id_rdv"));
        String valider = req.getParameter("valider");

        DataBaseService dataBaseService=new DataBaseService();
        boolean b = dataBaseService.updateRDV(id_rdv,valider);
        resp.getWriter().print(b);
    }
}
