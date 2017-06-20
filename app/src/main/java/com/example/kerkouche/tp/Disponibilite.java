package com.example.kerkouche.tp;

import java.io.Serializable;
import java.util.Date;


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