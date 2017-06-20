package com.example.kerkouche.myapplication.backend.Model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Created by lyes on 19/03/17.
 */

public class Commentaire implements Serializable {

    String Id_commentaire;
    String Id_annonce_comm;
    String Id_user_comm;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    String nom;
    String prenom;
    String text;

    String note;
    String date_comm;
    String time_comm;

    public String getDate_comm() {
        return date_comm;
    }

    public String getTime_comm() {
        return time_comm;
    }

    public void setDate_comm(String date_comm) {
        this.date_comm = date_comm;
    }

    public void setTime_comm(String time_comm) {
        this.time_comm = time_comm;
    }

    public Commentaire() {
    }

    public String getId_commentaire() {
        return Id_commentaire;
    }

    public void setId_commentaire(String id_commentaire) {
        Id_commentaire = id_commentaire;
    }

    public String getId_annonce_comm() {
        return Id_annonce_comm;
    }

    public void setId_annonce_comm(String id_annonce_comm) {
        Id_annonce_comm = id_annonce_comm;
    }

    public String getId_user_comm() {
        return Id_user_comm;
    }

    public void setId_user_comm(String id_user_comm) {
        Id_user_comm = id_user_comm;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}