package com.example.kerkouche.myapplication.backend.Model;

/**
 * Created by ubuntu on 23/05/17.
 */

public class Annonce_s {
    int id;
    String titre;
    String description;
    String url;
    String region;
    double latitude,longitude;
    int prix,nbrChambres,surface;
    String typelogm;

    public Annonce_s() {
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getRegion() {
        return region;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getPrix() {
        return prix;
    }

    public int getNbrChambres() {
        return nbrChambres;
    }

    public int getSurface() {
        return surface;
    }

    public String getTypelogm() {
        return typelogm;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setNbrChambres(int nbrChambres) {
        this.nbrChambres = nbrChambres;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public void setTypelogm(String typelogm) {
        this.typelogm = typelogm;
    }


}
