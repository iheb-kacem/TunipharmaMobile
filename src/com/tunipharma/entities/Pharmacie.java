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
public class Pharmacie {
    
    private int id;
    private String name;
    private String adresse;
    private int codePostal;
    private String type;
    private String etat;
    private String numTel;
    private String eMail;
    private Localisation location;
    private int pharmacien;

    public Pharmacie() {
    }

    public Pharmacie(String name, String adresse, int codePostal, String type, String etat, String numTel, String eMail, Localisation location, int pharmacien) {
        this.name = name;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.type = type;
        this.etat = etat;
        this.numTel = numTel;
        this.eMail = eMail;
        this.location = location;
        this.pharmacien = pharmacien;
    }

    public Pharmacie(String name, String adresse, int codePostal, String type, String etat) {
        this.name = name;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.type = type;
        this.etat = etat;
    }

    public Pharmacie(int id, String name, String adresse, int codePostal, String type, String etat, String numTel, String eMail, Localisation location, int pharmacien) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.type = type;
        this.etat = etat;
        this.numTel = numTel;
        this.eMail = eMail;
        this.location = location;
        this.pharmacien = pharmacien;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Localisation getLocation() {
        return location;
    }

    public void setLocation(Localisation location) {
        this.location = location;
    }

    public int getPharmacien() {
        return pharmacien;
    }

    public void setPharmacien(int pharmacien) {
        this.pharmacien = pharmacien;
    }

    public String toString() {
        return type + " " + name ;
    }

    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pharmacie other = (Pharmacie) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

   
    
    
      
}
