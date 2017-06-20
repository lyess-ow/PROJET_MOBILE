package com.example.kerkouche.tp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lyes on 19/03/17.
 */

public class User implements Serializable  {
    String nom;
    String prenom;

    public User() {
    }

    String username;
    String password;

    public User(String nom, String prenom, String username, String password, String email, List<Disponibilite> listDisponibilite, List<Annonce> listannonce, List<RendezVous> listRdv) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.password = password;
        this.email = email;
        this.listDisponibilite = listDisponibilite;
        this.listannonce = listannonce;
        this.listRdv = listRdv;
    }

    String email;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Disponibilite> getListDisponibilite() {
        return listDisponibilite;
    }

    public void setListDisponibilite(Disponibilite disponibilite) {
        this.listDisponibilite.add(disponibilite);
    }

    public List<Annonce> getListannonce() {
        return listannonce;
    }

    public void setListannonce(Annonce annonce) {
        this.listannonce.add(annonce);
    }

    public List<RendezVous> getListRdv() {
        return listRdv;
    }

    public void setListRdv(RendezVous rdv) {
        this.listRdv.add(rdv);
    }

    List<Disponibilite> listDisponibilite; //disponibilite quand il peut faire visiter le logement
    List<Annonce> listannonce; //les annonces que l'utilisateur possede
    List<RendezVous> listRdv; //la liste de RDV de l'utilisateur
}
