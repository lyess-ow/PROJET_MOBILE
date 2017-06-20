package com.example.kerkouche.myapplication.backend.Model;

import java.io.Serializable;
import java.util.List;

public class Annonce implements Serializable {
    int id;
    String titre;
    public String[] getListeImageDetai() {
        return listeImageDetai;
    }

    public void setListeImageDetai(String[] listeImageDetai) {
        this.listeImageDetai = listeImageDetai;
    }

    String [] listeImageDetai;

    public int getId() {
        return id;
    }

    public Annonce() {
    }

    public void setId(int id) {
        this.id = id;
    }


    String description;
    String utilisateur;
    int petiteImage;
    int[] grandeImage;
    String region;

    public int[] getGrandeImage() {
        return grandeImage;
    }

    public void setGrandeImage(int[] grandeImage) {
        this.grandeImage = grandeImage;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public int getPetiteImage() {
        return petiteImage;
    }

    public void setPetiteImage(int petiteImage) {
        this.petiteImage = petiteImage;
    }


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    public String getTypelogm() {
        return typelogm;
    }

    public void setTypelogm(String typelogm) {
        this.typelogm = typelogm;
    }

    public List<Commentaire> getListCommentaires() {
        return listCommentaires;
    }

    public void setListCommentaires(Commentaire commentaire) {
        this.listCommentaires.add(commentaire);
    }

    public List<RendezVous> getListrdv() {
        return listrdv;
    }

    public void setListrdv(RendezVous rendezVous) {
        this.listrdv.add(rendezVous);
    }




    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNbrChambres() {
        return nbrChambres;
    }

    public void setNbrChambres(int nbrChambres) {
        this.nbrChambres = nbrChambres;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }


    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    double latitude,longitude;
    int prix,nbrChambres,surface;
    String typelogm;

    List<Commentaire> listCommentaires;
    List<RendezVous> listrdv;

}