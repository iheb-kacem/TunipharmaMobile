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
public class DelegHandler extends DefaultHandler {

    private Vector deleg;
    String deleTag = "close";

    public DelegHandler() {
        deleg = new Vector();
    }

    public delegation[] getdele() {
        delegation[] delegs = new delegation[deleg.size()];

        deleg.copyInto(delegs);

        return delegs;


    }
    private delegation currentdeleg;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("Gouv")) {

            if (currentdeleg != null) {
                throw new IllegalStateException("already processing ville");
            }
            currentdeleg = new delegation();
        } else if (qName.equals("Delegation")) {
            deleTag = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("Gouv")) {
            // we are no longer processing a <reg.../> tag
            deleg.addElement(currentdeleg);
            System.out.println("per" + currentdeleg.toString());


           currentdeleg = null;
        } else if (qName.equals("Delegation")) {
            deleTag = "close";
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentdeleg != null) {

            // don't forget to trim excess spaces from the ends of the string
            if (deleTag.equals("open")) {
                String g = new String(ch, start, length).trim();
                currentdeleg.getDelegation();
                System.out.println("id" + currentdeleg.getDelegation());
            }
        }
    }
}
    

