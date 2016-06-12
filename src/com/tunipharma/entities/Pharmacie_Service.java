/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunipharma.entities;

/**
 *
 * @author Ferdawes
 */
public class Pharmacie_Service {
    private Pharmacie id_pharm;
    private Service id_serv;
    private String date;

    public Pharmacie_Service(){
        
    }
    public Pharmacie_Service(Pharmacie id_pharm, Service id_serv,String date) {
        this.id_pharm = id_pharm;
        this.id_serv = id_serv;
        this.date=date;
    }

    public Pharmacie getId_pharm() {
        return id_pharm;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId_pharm(Pharmacie id_pharm) {
        this.id_pharm = id_pharm;
    }

    public Service getId_serv() {
        return id_serv;
    }

    public void setId_serv(Service id_serv) {
        this.id_serv = id_serv;
    }

//    public void setId_serv(int id) {
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }


   
}
