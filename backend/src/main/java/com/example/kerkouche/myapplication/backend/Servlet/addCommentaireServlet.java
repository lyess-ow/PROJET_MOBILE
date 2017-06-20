package com.example.kerkouche.myapplication.backend.Servlet;

import com.example.kerkouche.myapplication.backend.Model.Commentaire;
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

public class addCommentaireServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String Id_annonce_comm = req.getParameter("Id_annonce_comm");
        String Id_user_comm = req.getParameter("Id_user_comm");
        String Text = req.getParameter("Text");
        String Note = req.getParameter("Note");
        String Date_comm = req.getParameter("Date_comm");
        String Time_comm = req.getParameter("Time_comm");

        Commentaire commentaire = new Commentaire();
        commentaire.setId_annonce_comm(Id_annonce_comm);
        commentaire.setId_user_comm(Id_user_comm);
        commentaire.setText(Text);
        commentaire.setNote(Note);
        commentaire.setDate_comm(Date_comm);
        commentaire.setTime_comm(Time_comm);

        DataBaseService dataBaseService=new DataBaseService();
        boolean b = dataBaseService.addCommentaire(commentaire);
        resp.getWriter().print(b);
    }
}
