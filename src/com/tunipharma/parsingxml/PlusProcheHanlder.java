/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunipharma.parsingxml;

import com.tunipharma.entities.Localisation;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Ferdawes
 */
public class PlusProcheHanlder extends DefaultHandler {

    private Vector pharmacies;
    private String IDTag = "close";
    private String nomTag = "close";
    private String adresseTag = "close";
    private String codepostalTag = "close";
    private String typeTag = "close";
    private String etatTag = "close";
    private String numtelTag = "close";
    private String emailTag = "close";
    private String logTag = "close";
    private String latTag = "close";
    private String pharmacienTag = "close";
    private String Date_InscriptionTag = "close";
    private String distanceTag = "close";
    private com.tunipharma.entities.Pharmacie plusProche;
    private Localisation localisation;
    private String distance;

    public PlusProcheHanlder() {
        plusProche = null;
        localisation = null;
        pharmacies = new Vector();
        pharmacies.addElement(plusProche);

    }

    public com.tunipharma.entities.Pharmacie getPharmacie() {

            return plusProche;


    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("plusProche")) {

            if (plusProche != null) {
                throw new IllegalStateException("already processing a personnes");
            }
            plusProche = new com.tunipharma.entities.Pharmacie();
            localisation = new Localisation(0.0, 0.0);

        } else if (qName.equals("ID")) {
            IDTag = "open";
        } else if (qName.equals("nom")) {
            nomTag = "open";
        } else if (qName.equals("adresse")) {
            adresseTag = "open";
        } else if (qName.equals("codepostal")) {
            codepostalTag = "open";
        } else if (qName.equals("type")) {
            typeTag = "open";
        } else if (qName.equals("etat")) {
            etatTag = "open";
        } else if (qName.equals("numtel")) {
            numtelTag = "open";
        } else if (qName.equals("email")) {
            emailTag = "open";
        } else if (qName.equals("log")) {
            logTag = "open";
        } else if (qName.equals("lat")) {
            latTag = "open";
        } else if (qName.equals("pharmacien")) {
            pharmacienTag = "open";
        } else if (qName.equals("Date_Inscription")) {
            Date_InscriptionTag = "open";
        } else if (qName.equals("distance")) {
            distanceTag = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("pharmacie")) {
            // we are no longer processing a <reg.../> tag
            pharmacies.addElement(plusProche);
            System.out.println("per" + pharmacies.toString());


            plusProche = null;
        } else if (qName.equals("ID")) {
            IDTag = "close";
        } else if (qName.equals("nom")) {
            nomTag = "close";
        } else if (qName.equals("adresse")) {
            adresseTag = "close";
        } else if (qName.equals("codepostal")) {
            codepostalTag = "close";
        } else if (qName.equals("type")) {
            typeTag = "close";
        } else if (qName.equals("etat")) {
            etatTag = "close";
        } else if (qName.equals("numtel")) {
            numtelTag = "close";
        } else if (qName.equals("email")) {
            emailTag = "close";
        } else if (qName.equals("log")) {
            logTag = "close";
        } else if (qName.equals("lat")) {
            latTag = "close";
        } else if (qName.equals("pharmacien")) {
            pharmacienTag = "close";
        } else if (qName.equals("Date_Inscription")) {
            Date_InscriptionTag = "close";
        } else if (qName.equals("distance")) {
            distanceTag = "close";
        }

    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (plusProche != null) {

            if (IDTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                plusProche.setId(Integer.parseInt(id));
            } else if (nomTag.equals("open")) {
                String nom = new String(ch, start, length).trim();
                plusProche.setName(nom);
            } else if (adresseTag.equals("open")) {
                String adresse = new String(ch, start, length).trim();
                plusProche.setAdresse(adresse);
            } else if (codepostalTag.equals("open")) {
                String codePostal = new String(ch, start, length).trim();
                plusProche.setCodePostal(Integer.parseInt(codePostal));
            } else if (typeTag.equals("open")) {
                String type = new String(ch, start, length).trim();
                plusProche.setType(type);
            } else if (etatTag.equals("open")) {
                String etat = new String(ch, start, length).trim();
                plusProche.setEtat(etat);
            } else if (numtelTag.equals("open")) {
                String numTel = new String(ch, start, length).trim();
                plusProche.setNumTel(numTel);
            } else if (emailTag.equals("open")) {
                String email = new String(ch, start, length).trim();
                plusProche.seteMail(email);
            } else if (logTag.equals("open")) {
                String log = new String(ch, start, length).trim();
                localisation.setLon(Double.parseDouble(log));
                plusProche.setLocation(localisation);
            } else if (latTag.equals("open")) {
                String lat = new String(ch, start, length).trim();
                localisation.setLat(Double.parseDouble(lat));
                plusProche.setLocation(localisation);
            } else if (pharmacienTag.equals("open")) {
                String pharmacien = new String(ch, start, length).trim();
                plusProche.setPharmacien(Integer.parseInt(pharmacien));
            } else if (distanceTag.equals("open")) {
                distance = new String(ch, start, length).trim();
            }
        }
    }
}