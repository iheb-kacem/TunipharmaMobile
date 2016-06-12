package com.tunipharma.service;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Wael Mallek
 */
public class ServiceHandler extends DefaultHandler{
    private Vector services;
    String nomTag = "close";
    String catTag = "close";

    public ServiceHandler() {
        services = new Vector();
    }

    public Service[] getService() {
        Service[] servicess = new Service[services.size()];
        services.copyInto(servicess);
        return servicess;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Service currentService;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("service")) {

            if (currentService != null) {
                throw new IllegalStateException("already processing a personnes");
            }
            currentService = new Service();
        } else if (qName.equals("nom")) {
            nomTag = "open";
        } else if (qName.equals("categorie")) {
            catTag = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("service")) {
            // we are no longer processing a <reg.../> tag
            services.addElement(currentService);
            currentService = null;
        } else if (qName.equals("nom")) {
            nomTag = "close";
        } else if (qName.equals("categorie")) {
            catTag = "close";
        }
    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentService != null) {
            // don't forget to trim excess spaces from the ends of the string
                if (nomTag.equals("open")) {
                String nom = new String(ch, start, length).trim();
                currentService.setNom(nom);
            } else
                    if (catTag.equals("open")) {
                String categorie = new String(ch, start, length).trim();
                currentService.setCategorie(categorie);
            }
        }
    }
    
}
