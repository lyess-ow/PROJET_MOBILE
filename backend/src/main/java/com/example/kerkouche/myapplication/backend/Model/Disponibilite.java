package com.example.kerkouche.myapplication.backend.Model;

import java.io.Serializable;


public class Disponibilite implements Serializable {

    String datedispoDebut;
    String datedispofin;

    public String getDatedispoDebut() {
        return datedispoDebut;
    }

    public void setDatedispoDebut(String datedispoDebut) {
        this.datedispoDebut = datedispoDebut;
    }

    public String getDatedispofin() {
        return datedispofin;
    }

    public void setDatedispofin(String datedispofin) {
        this.datedispofin = datedispofin;
    }

    public Disponibilite() {
    }
}