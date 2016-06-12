/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunipharma.entities;

/**
 *
 * @author asma
 */
public class Service {
    private int id ;
    private String nom ;
    private String categorie;
    private String etat ;
    private String description;
//constructeur
    public Service() {
    }

    public Service(int id, String nom, String categorie, String etat,String description) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.etat = etat;
        this.description=description;
    }

//getter&setter
    
    public int getId() {
        return id;
    }
public String getDescription() {
        return description;
    }
    public String getNom() {
        return nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getEtat() {
        return etat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    

    public void setEtat(String etat) {
        this.etat = etat;
    }
  public void setDescription(String description) {
        this.description = description;
    }

    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Service other = (Service) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public String toString() {
        return nom;
    }

    public void setCategorie(String categorie) {
        this.categorie=categorie;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

   

    

   
    
    
}
