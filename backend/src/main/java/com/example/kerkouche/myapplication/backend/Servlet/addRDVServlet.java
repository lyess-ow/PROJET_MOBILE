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

public class addRDVServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String Id_annonce_rdv = req.getParameter("Id_annonce_rdv");
        String Id_user_rdv = req.getParameter("Id_user_rdv");
        String Id_proprio_rdv = req.getParameter("Id_proprio_rdv");
        String Lieu = req.getParameter("Lieu");
        String Date_rdv = req.getParameter("Date_rdv");
        String Heure_rdv = req.getParameter("Heure_rdv");
        String Valider = req.getParameter("Valider");

        RendezVous rendezVous = new RendezVous();
        rendezVous.setId_annonce_rdv(Id_annonce_rdv);
        rendezVous.setId_user_rdv(Id_user_rdv);
        rendezVous.setId_proprio_rdv(Id_proprio_rdv);
        rendezVous.setLieu(Lieu);
        rendezVous.setDate_rdv(Date.valueOf(Date_rdv));
        rendezVous.setHeure_rdv(Time.valueOf(Heure_rdv));
        rendezVous.setValider(Valider);

        DataBaseService dataBaseService=new DataBaseService();
        boolean b = dataBaseService.addRDV(rendezVous);
        resp.getWriter().print(b);

    }
}
