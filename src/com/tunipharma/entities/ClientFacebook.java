/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tunipharma.entities;

import java.util.Date;

/**
 *
 * @author KINGMOEZHAJ
 */
public class ClientFacebook {
    
    private long ID;
    private String Nom;
    private String Pseudo;
    private String Email;
    private Date Birthday;

    public ClientFacebook(long ID, String Nom, String Pseudo, String Email, Date Birthday) {
        this.ID = ID;
        this.Nom = Nom;
        this.Pseudo = Pseudo;
        this.Email = Email;
        this.Birthday = Birthday;
    }

    public long getID() {
        return ID;
    }

    public String getNom() {
        return Nom;
    }

    public String getPseudo() {
        return Pseudo;
    }

    public String getEmail() {
        return Email;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public String toString() {
        return "ClientFacebook{" + "ID=" + ID + ", Nom=" + Nom + ", Pseudo=" + Pseudo + ", Email=" + Email + ", Birthday=" + Birthday + '}';
    }

    
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.ID ^ (this.ID >>> 32));
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClientFacebook other = (ClientFacebook) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }
    
    
}
