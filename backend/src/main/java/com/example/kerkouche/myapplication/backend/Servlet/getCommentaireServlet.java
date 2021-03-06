package com.example.kerkouche.myapplication.backend.Servlet;

import com.example.kerkouche.myapplication.backend.Model.Commentaire;
import com.example.kerkouche.myapplication.backend.Model.Disponibilite;
import com.example.kerkouche.myapplication.backend.Service.DataBaseService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kerkouche on 23/05/17.
 */

public class getCommentaireServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_annonce = req.getParameter("id_annonce");

        List<Commentaire> commentaireList = new DataBaseService().getCommentaireList(Integer.parseInt(id_annonce));
        Gson gson = new Gson();
        resp.getWriter().print(gson.toJson(commentaireList));
    }
}
