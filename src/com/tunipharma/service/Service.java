/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tunipharma.service;

/**
 *
 * @author PLAYER9
 */
public class Service {

    private String nom;
    private String categorie;

    public Service(String nom, String categorie) {
        this.nom = nom;
        this.categorie = categorie;
    }

    public Service() {
    }


    public String getNom() {
        return nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String toString() {
        return "Personne{" + ", nom=" + nom + ", categorie=" + categorie + '}';
    }

    Service getPersonne() {
        return null;
    }
}
