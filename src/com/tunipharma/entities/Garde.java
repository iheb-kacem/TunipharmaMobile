/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunipharma.entities;

import java.util.Date;

/**
 *
 * @author PLAYER9
 */
public class Garde {

    private Pharmacie pharmacie;
    private Date jour;

    public Garde(Pharmacie pharmacie, Date jour) {
        this.pharmacie = pharmacie;
        this.jour = jour;
    }

    public Pharmacie getPharmacie() {
        return pharmacie;
    }

    public void setPharmacie(Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
    }

    public Date getJour() {
        return jour;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    public String toString() {
        return "Garde{" + "pharmacie=" + pharmacie + ", jour=" + jour + '}';
    }

    public int hashCode() {
        int hash = 7;
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Garde other = (Garde) obj;
        
        return true;
    }

    
    
}