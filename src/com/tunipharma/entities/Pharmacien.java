/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tunipharma.entities;


/**
 *
 * @author KINGMOEZHAJ
 */
public class Pharmacien {
    
    private int ref;
    private String nom;
    private String prenom;
    private int cin;
    private String etat;
    private String numTel;
    private String eMail;

    public Pharmacien() {

    }
    
    public Pharmacien(int ref, String nom, String prenom, int cin, String etat, String numTel, String eMail) {
        this.ref = ref;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.etat = etat;
        this.numTel = numTel;
        this.eMail = eMail;
    }

    public Pharmacien(String nom, String prenom, int cin, String etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.etat = etat;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }
    
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

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String toString() {
        return "Pharmacien " + nom + " " + prenom ;
    }

    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.ref;
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pharmacien other = (Pharmacien) obj;
        if (this.ref != other.ref) {
            return false;
        }
        if (this.cin != other.cin) {
            return false;
        }
        return true;
    }
    
    
    
}
