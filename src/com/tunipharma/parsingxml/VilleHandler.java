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
 * @author Ferdawes
 */
public class VilleHandler extends DefaultHandler {

    private Vector ville;
    String gouvTag = "close";

    public VilleHandler() {
        ville = new Vector();
    }

    public Ville[] getville() {
        Ville[] villess = new Ville[ville.size()];

        ville.copyInto(villess);

        return villess;


    }
    private Ville currentVille;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("Gouv")) {

            if (currentVille != null) {
                throw new IllegalStateException("already processing ville");
            }
            currentVille = new Ville();
        } else if (qName.equals("Gouvernorat")) {
            gouvTag = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("Gouv")) {
            // we are no longer processing a <reg.../> tag
            ville.addElement(currentVille);
            System.out.println("per" + currentVille.toString());


            currentVille = null;
        } else if (qName.equals("Gouvernorat")) {
            gouvTag = "close";
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentVille != null) {

            // don't forget to trim excess spaces from the ends of the string
            if (gouvTag.equals("open")) {
                String g = new String(ch, start, length).trim();
                currentVille.setGouv(g);
                System.out.println("id" + currentVille.getGouv());
            }
        }
    }
}