/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunipharma.parsingxml;

/**
 *
 * @author Houcem
 */
public class CoordonneesPharma {
    private int     id;
    private String  nom; 
    private double  log;
    private  double lat;
    
    
    public CoordonneesPharma(int id,String nom,double lat,double log){
    
      this.id = id;
      this.nom = nom; 
      this.log = log;
      this.lat = lat;
        
    }
    public CoordonneesPharma(){}; 
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
    void setId(String id) {
        this.id = Integer.parseInt(id+"");
    } 
    void setLog(String log) {
        this.log = Double.parseDouble(log+"");
    }
          void setLat(String lat) {
        this.lat = Double.parseDouble(lat+"");
    }
          public String toString() {
        return "Coordonees{" + "id=" + id + ", nom=" + nom +  '}';
    }

   CoordonneesPharma getCoordonneesPharma() {
        return null;
    }
}
