package com.example.kerkouche.myapplication.backend.Servlet;


import com.example.kerkouche.myapplication.backend.Model.Annonce_s;
import com.example.kerkouche.myapplication.backend.Service.DataBaseService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ubuntu on 27/05/17.
 */

public class rechercheServlet  extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String dens = req.getParameter("dens");
        String region = req.getParameter("region");
        String tlogm = req.getParameter("tlgm");

        Gson gson = new Gson();
        List<Annonce_s> listeAnnonce = new DataBaseService().Recherche(dens, region,tlogm );
        resp.getWriter().print(gson.toJson(listeAnnonce));
    }


}