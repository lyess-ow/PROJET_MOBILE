package com.example.kerkouche.myapplication.backend.Servlet;

import com.example.kerkouche.myapplication.backend.Model.Annonce;
import com.example.kerkouche.myapplication.backend.Service.DataBaseService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kerkouche on 14/05/17.
 */

public class getDetailServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String dens = req.getParameter("dens");
        int id = Integer.parseInt(req.getParameter("idanc"));
        Gson gson = new Gson();
        List<Annonce> listeAnnonce = new DataBaseService().getDetailAnnaonces(dens,id);
        resp.getWriter().print(gson.toJson(listeAnnonce));
    }
}
