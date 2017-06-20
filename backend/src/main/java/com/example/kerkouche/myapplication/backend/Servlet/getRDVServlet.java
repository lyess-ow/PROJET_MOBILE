package com.example.kerkouche.myapplication.backend.Servlet;

import com.example.kerkouche.myapplication.backend.Model.Disponibilite;
import com.example.kerkouche.myapplication.backend.Model.RendezVous;
import com.example.kerkouche.myapplication.backend.Service.DataBaseService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kerkouche on 14/05/17.
 */

public class getRDVServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Id_proprio_rdv = req.getParameter("Id_proprio_rdv");

        List<RendezVous> rdvList = new DataBaseService().getRDVList(Integer.parseInt(Id_proprio_rdv));
        Gson gson = new Gson();
        resp.getWriter().print(gson.toJson(rdvList));
    }
}
