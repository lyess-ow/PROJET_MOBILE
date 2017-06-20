package com.example.kerkouche.tp;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;


public class RendezVous implements Serializable {
    String id_rdv;
    String id_annonce_rdv;
    String id_user_rdv;
    String id_proprio_rdv;
    String Lieu;
    java.sql.Date Date_rdv;

    String annonce_titre;
    String nom_user_rdv;
    String prenom_user_rdv;

    String date2_rdv;
    String time_rdv;

    int sync_status;

    public RendezVous(String id_rdv, int sync_status, String valider) {
        this.id_rdv = id_rdv;
        this.sync_status = sync_status;
        Valider = valider;
    }

    public int getSync_status() {
        return sync_status;
    }

    public void setSync_status(int sync_status) {
        this.sync_status = sync_status;
    }

    public String getDate2_rdv() {
        return date2_rdv;
    }

    public void setDate2_rdv(String date2_rdv) {
        this.date2_rdv = date2_rdv;
    }

    public String getTime_rdv() {
        return time_rdv;
    }

    public void setTime_rdv(String time_rdv) {
        this.time_rdv = time_rdv;
    }

    public String getNom_user_rdv() {
        return nom_user_rdv;
    }

    public void setNom_user_rdv(String nom_user_rdv) {
        this.nom_user_rdv = nom_user_rdv;
    }

    public String getPrenom_user_rdv() {
        return prenom_user_rdv;
    }

    public void setPrenom_user_rdv(String prenom_user_rdv) {
        this.prenom_user_rdv = prenom_user_rdv;
    }

    public String getAnnonce_titre() {
        return annonce_titre;
    }

    public void setAnnonce_titre(String annonce_titre) {
        this.annonce_titre = annonce_titre;
    }

    public String getId_rdv() {
        return id_rdv;
    }

    public void setId_rdv(String id_rdv) {
        this.id_rdv = id_rdv;
    }

    public String getId_annonce_rdv() {
        return id_annonce_rdv;
    }

    public void setId_annonce_rdv(String id_annonce_rdv) {
        this.id_annonce_rdv = id_annonce_rdv;
    }

    public String getId_user_rdv() {
        return id_user_rdv;
    }

    public void setId_user_rdv(String id_user_rdv) {
        this.id_user_rdv = id_user_rdv;
    }

    public String getId_proprio_rdv() {
        return id_proprio_rdv;
    }

    public void setId_proprio_rdv(String id_proprio_rdv) {
        this.id_proprio_rdv = id_proprio_rdv;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String lieu) {
        Lieu = lieu;
    }

    public java.sql.Date getDate_rdv() {
        return Date_rdv;
    }

    public void setDate_rdv(java.sql.Date date_rdv) {
        Date_rdv = date_rdv;
    }

    public String getValider() {
        return Valider;
    }

    public void setValider(String valider) {
        Valider = valider;
    }

    public Time getHeure_rdv() {
        return Heure_rdv;
    }

    public void setHeure_rdv(Time heure_rdv) {
        Heure_rdv = heure_rdv;
    }

    String Valider;
    Time Heure_rdv;

    public RendezVous() {
    }
}