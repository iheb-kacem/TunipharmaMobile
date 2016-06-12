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
public class Commentaire {
    
    private ClientFacebook clientFacebook;
    private Pharmacie pharmacie;
    private Date date_commentaire;
    private int note;
    private String commentaire;

    public Commentaire(ClientFacebook clientFacebook, Pharmacie pharmacie, Date date_commentaire, int note, String commentaire) {
        this.clientFacebook = clientFacebook;
        this.pharmacie = pharmacie;
        this.date_commentaire = date_commentaire;
        this.note = note;
        this.commentaire = commentaire;
    }
    public Commentaire(ClientFacebook clientFacebook, Pharmacie pharmacie,  int note, String commentaire) {
        this.clientFacebook = clientFacebook;
        this.pharmacie = pharmacie;
        this.date_commentaire = date_commentaire;
        this.note = note;
        this.commentaire = commentaire;
    }

    

    public Pharmacie getPharmacie() {
        return pharmacie;
    }

    public ClientFacebook getClientFacebook() {
        return clientFacebook;
    }

    public int getNote() {
        return note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String toString() {
        return clientFacebook.getNom() + " (note=" + note + ") : " + commentaire + '}';
    }

    public int hashCode() {
        int hash = 3;
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commentaire other = (Commentaire) obj;
        
        return true;
    }
    
    
    
}
