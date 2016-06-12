/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunipharma.parsingxml;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 *
 * @author Houcem
 */
public class CoordonneesPharmaHandler extends DefaultHandler{
    private Vector Coordonnees;
    String idTag = "close";
    String nomTag = "close";
    String logTag = "close";
    String latTag = "close";
    
    public CoordonneesPharmaHandler(){
    Coordonnees = new Vector();
    }
    public CoordonneesPharma[] getCoordonneesPharma(){
       CoordonneesPharma[] Coordonnee = new CoordonneesPharma[Coordonnees.size()];
     
        Coordonnees.copyInto(Coordonnee);
        return Coordonnee;
        
    }
    private CoordonneesPharma currentCoordonnees;

    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("pharmacie")) {

            if ( currentCoordonnees != null) {
                throw new IllegalStateException("already processing coordonnee");
            }
            currentCoordonnees = new CoordonneesPharma();
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("nom")) {
            nomTag = "open";
        } else if (qName.equals("log")) {
            logTag = "open";
        }else if (qName.equals("lat")) {
            latTag = "open";
        }
   }
public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("pharmacie")) {
            // we are no longer processing a <reg.../> tag
            Coordonnees.addElement(currentCoordonnees);
            currentCoordonnees = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("nom")) {
            nomTag = "close";
        } else if (qName.equals("log")) {
            logTag = "close";
        }else if (qName.equals("lat")) {
            latTag = "close";
        }
    }
public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentCoordonnees != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentCoordonnees.setId(id);
            } else
                if (nomTag.equals("open")) {
                String nom = new String(ch, start, length).trim();
                currentCoordonnees.setNom(nom);
            } else
                    if (logTag.equals("open")) {
                String log = new String(ch, start, length).trim();
                currentCoordonnees.setLog(log);
            }else
                    if (latTag.equals("open")) {
                String lat = new String(ch, start, length).trim();
                currentCoordonnees.setLat(lat);
            }
        }
    }
}
