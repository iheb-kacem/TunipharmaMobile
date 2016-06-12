/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunipharma.parsingxml;

/**
 *
 * @author Ferdawes
 */
public class Pharmacie {

    private int id;
    private String nom;
    private String add;
    private String etat;
    private String cd;
    private String type;
    private String num;
    private String email;
    private double log;
    private double lat;
    private int id_pha;
  private String jours;
   
    private String date;
    private String lieu;
    private String gouv;
    private String deleg;
    public Pharmacie(int id, String nom, String add, String etat, String cd, String type, String num, String jours,String email,String gouv,String deleg,String date, double log, double lat, int id_pha) {
        this.id = id;
        this.nom = nom;
        this.add = add;
        this.etat = etat;
        this.date=date;
        this.cd = cd;
        this.jours=jours;
        this.type = type;
        this.num = num;
        this.email = email;
        this.log = log;
        this.lat = lat;
        this.gouv = gouv;
        this.deleg = deleg;
        this.id_pha = id_pha;
    }

    public Pharmacie() {
    }
    

    public int getId() {
        return id;
    }

    public String getJours() {
        return jours;
    }

    public void setJours(String jours) {
        this.jours = jours;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGouv() {
        return gouv;
    }

    public void setGouv(String gouv) {
        this.gouv = gouv;
    }

    public String getDeleg() {
        return deleg;
    }

    public void setDeleg(String deleg) {
        this.deleg = deleg;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getId_pha() {
        return id_pha;
    }

    public void setId_pha(int id_pha) {
        this.id_pha = id_pha;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    void setId(String id) {
        this.id = Integer.parseInt(id + "");
    }

    void setLog(String log) {
        this.log = Double.parseDouble(log + "");
    }

    void setLat(String lat) {
        this.lat = Double.parseDouble(lat + "");
    }

    void setId_pha(String id) {
        this.id_pha = Integer.parseInt(id + "");
    }

    public String toString() {
        return "Personne{" + "id=" + id + ", nom=" + nom + ", prenom=" + add + "lieu=" + deleg + " " + gouv + '}';
    }

    Pharmacie getPharmacie() {
        return null;
   
}
      public String Affiche() {
        return "Pharmacie:"+"\n"
                +  "* nom=" + nom +"\n"
                + " * adresse=" + add +"\n" 
                + " * etat=" + etat +"\n"
                + " * code postal=" + cd +"\n"
                + " *type=" + type +"\n"
                + " *téléphone=" + num +"\n"
                + " *email=" + email  +"\n"
                + " *lieu=" + gouv +"\n";
    }
}
