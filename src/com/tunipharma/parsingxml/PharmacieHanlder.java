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
public class PharmacieHanlder extends DefaultHandler{
     private Vector pharmacies;
    String idTag = "close";
    String nomTag = "close";
    String addTag = "close";
    String cpTag = "close";
    String typeTag = "close";
    String etatTag = "close";
    String numTag = "close";
    String emailTag = "close";
    String GouvTag="close";
    String DelegTag="close";
   String jourTag="close";
    String logTag = "close";
    String latTag = "close";
    String id_phTag = "close";
    String dateTag = "close";

    public PharmacieHanlder() {
      pharmacies = new Vector();
    
    }

   

   
    public Pharmacie[] getPharmacie(){
       Pharmacie[] pharmaciess = new Pharmacie[pharmacies.size()];
     
        pharmacies.copyInto(pharmaciess);
        return pharmaciess;
        
    }
        private Pharmacie currentPersonne;
         public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("pharmacie")) {

            if (currentPersonne != null) {
                throw new IllegalStateException("already processing a personnes");
            }
            currentPersonne = new Pharmacie();
        } else if (qName.equals("ID")) {
            idTag = "open";
        } 
        else if (qName.equals("nom")) {
            nomTag = "open";
        } 
         else if (qName.equals("adresse")) {
            addTag = "open";
        } 
         else if (qName.equals("codepostal")) {
            cpTag = "open";
        } 
         else if (qName.equals("type")) {
            typeTag = "open";
        } 
         else if (qName.equals("jour")) {
            jourTag = "open";
        } 
         else if (qName.equals("etat")) {
            etatTag = "open";
        } 
         else if (qName.equals("numtel")) {
            numTag = "open";
        } 
         else if (qName.equals("email")) {
            emailTag = "open";
        } 
         else if (qName.equals("Gouvernorat")) {
            GouvTag = "open";
        } 
          else if (qName.equals("Delegation")) {
            DelegTag = "open";
        } 
        else if (qName.equals("log")) {
            logTag = "open";
        } 
        else if (qName.equals("lat")) {
            latTag = "open";
        } 
        else if (qName.equals("pharmacien")) {
            id_phTag = "open";
        } 
        else if (qName.equals("Date_Inscription")) {
            dateTag = "open";
        } 
         }
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("pharmacie")) {
            // we are no longer processing a <reg.../> tag
           pharmacies.addElement(currentPersonne);
            System.out.println("per"+ pharmacies.toString());

            
            currentPersonne = null;
        } else if (qName.equals("ID")) {
            idTag = "close";
        }
        else if (qName.equals("nom")) {
            nomTag = "close";
        } 
         else if (qName.equals("adresse")) {
            addTag = "close";
        } 
         else if (qName.equals("codepostal")) {
            cpTag = "close";
        } 
          else if (qName.equals("jour")) {
            jourTag = "close";
        } 
         else if (qName.equals("Gouvernorat")) {
            GouvTag = "close";
        } 
         else if (qName.equals("Delegation")) {
            DelegTag = "close";
        } 
         else if (qName.equals("type")) {
            typeTag = "close";
        } 
         else if (qName.equals("etat")) {
            etatTag = "close";
        } 
         else if (qName.equals("numtel")) {
            numTag = "close";
        } 
         else if (qName.equals("email")) {
            emailTag = "close";
        } 
        else if (qName.equals("log")) {
            logTag = "close";
        } 
        else if (qName.equals("lat")) {
            latTag = "close";
        } 
        else if (qName.equals("pharmacien")) {
            id_phTag = "close";
        } 
        else if (qName.equals("Date_Inscription")) {
            dateTag = "close";
        } 
        
    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentPersonne != null) {
            
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentPersonne.setId(id);
                System.out.println("id"+currentPersonne.getId());
            } else
                if (nomTag.equals("open")) {
                String nom = new String(ch, start, length).trim();
                currentPersonne.setNom(nom);
                 System.out.println("nom"+currentPersonne.getNom());
            } else
                    if (addTag.equals("open")) {
                String add = new String(ch, start, length).trim();
                    currentPersonne.setAdd(add);
                    System.out.println("add"+currentPersonne.getAdd());
            }
                    else
                    if (jourTag.equals("open")) {
                String jour = new String(ch, start, length).trim();
                    currentPersonne.setJours(jour);
                    System.out.println("jour"+currentPersonne.getJours());
            }
            else
                    if (cpTag.equals("open")) {
                String cp = new String(ch, start, length).trim();
                    currentPersonne.setCd(cp);
                    System.out.println("cp"+currentPersonne.getCd());
                    }
              if (typeTag.equals("open")) {
                String type = new String(ch, start, length).trim();
                    currentPersonne.setType(type);
                   
                    }
              if (GouvTag.equals("open")) {
                String gouv = new String(ch, start, length).trim();
                    currentPersonne.setGouv(gouv);
                    System.out.println("gpiv"+currentPersonne.getGouv());
                    }
              if (DelegTag.equals("open")) {
                String deleg = new String(ch, start, length).trim();
                    currentPersonne.setDeleg(deleg);
                    System.out.println("lieu"+currentPersonne.getDeleg());
                    }
                if (etatTag.equals("open")) {
                String etat = new String(ch, start, length).trim();
                    currentPersonne.setEtat(etat);
                    }
                 if (numTag.equals("open")) {
                String num = new String(ch, start, length).trim();
                    currentPersonne.setNum(num);
                    }
                  if (emailTag.equals("open")) {
                String email = new String(ch, start, length).trim();
                    currentPersonne.setEmail(email);
                    }
                   if (logTag.equals("open")) {
                String log = new String(ch, start, length).trim();
                    currentPersonne.setLog(log);
                    
                    }
                    if (latTag.equals("open")) {
                String lat = new String(ch, start, length).trim();
                    currentPersonne.setLat(lat);
                   }
                      if (id_phTag.equals("open")) {
                String ph = new String(ch, start, length).trim();
                    currentPersonne.setId_pha(ph);
                   }
                      if (dateTag.equals("open")) {
                String date = new String(ch, start, length).trim();
                    currentPersonne.setDate(date);
                   }
                      
                   
        }
       
       
  
    }
    
    }

