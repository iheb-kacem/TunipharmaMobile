/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunipharma.parsingxml;

import com.tunipharma.display.MainDisplay;
import com.tunipharma.midlet.TuniPharma;
import com.tunipharma.service.DisplayService;
import java.io.DataInputStream;
import java.util.Vector;
import javax.microedition.amms.SoundSource3D;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Ferdawes
 */
public class DisplayRecherche implements CommandListener, Runnable {

    int cnx = 0;
    int i;
    int id;
    public static Form form = new Form("INFO PHARMACIE");
    Form f = new Form("Acceuil");
    TextField no = new TextField("Entrer le nom du pharmacie ", null, 30, TextField.ANY);
//    String[] items1;
    //{"Ariana", "Tunis", "Manouba", "Ben Arous", "Sousse", "Monastir"}
    TextField dele = new TextField("Entrer les pharmacie du deleg ", null, 30, TextField.ANY);
    Ticker t = new Ticker("Tunipharma");
    Command cmdParse = new Command("Pharmacie", Command.SCREEN, 0);
    Command cmdret = new Command("Back", Command.BACK, 0);
    Form loadingDialog = new Form("Please Wait");
    Pharmacie[] pharmacies;
    List lst = new List("Pharmacies", List.IMPLICIT);
    String items[] = {"Pharmacie de Jour", "Pharmacie de Nuit"};
    ChoiceGroup cgGender = new ChoiceGroup("Choisir l'etat du pharmacie", Choice.EXCLUSIVE, items, null);
    String g[] = {"Pharmacie en garde"};
    ChoiceGroup garde = new ChoiceGroup("voir les pharmacies en garde", Choice.MULTIPLE, g, null);
    StringBuffer sb = new StringBuffer();
    Command cmdBack = new Command("Back", Command.BACK, 0);
    Command cmdService = new Command("Services", Command.SCREEN, 0);
    boolean x = false;
    ChoiceGroup gouv;
    TuniPharma m;
    //retour midlet d'acceuil
    private final Command REQUEST_RETURN = new Command("Retour", Command.EXIT, 1);

    public DisplayRecherche(TuniPharma m) {

        this.m = m;
        x = true;
        Thread t = new Thread(this);
        t.start();
        try {
            t.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        x = false;

        f.append(no);
        f.append(gouv);
        f.append(dele);
        f.append(garde);
        f.append(cgGender);
        lst.setCommandListener(this);
        lst.addCommand(cmdret);

        form.addCommand(cmdBack);
        form.addCommand(cmdService);
        form.setCommandListener(this);

        f.addCommand(REQUEST_RETURN);
        f.addCommand(cmdParse);
        f.setCommandListener(this);
        m.disp.setCurrent(f);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdParse) {
            Thread th = new Thread(this);
            th.start();
        }
        if (c == cmdService) {
            new DisplayService(m, id);
        }
        if (c == REQUEST_RETURN) {
            m.disp.setCurrent(new MainDisplay(m));
        }
        if (d == lst && c == List.SELECT_COMMAND) {
            System.out.println("hhhgjjhg");
            form.append("Informations Pharmacie: \n");
            form.append(showPharma(lst.getString(lst.getSelectedIndex())));
            System.out.println("listeindex:" + lst.getString(lst.getSelectedIndex()));
            m.disp.setCurrent(form);
        }

        if (c == cmdBack) {
            form.deleteAll();
            m.disp.setCurrent(lst);
        }

        if (c == cmdret) {

            lst.deleteAll();
            m.disp.setCurrent(f);

        }

    }

    public void run() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (x) {
            try {

                VilleHandler vil = new VilleHandler();


                // get a parser object
                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();

                // get an InputStream from somewhere (could be HttpConnection, for example)
                HttpConnection hc = (HttpConnection) Connector.open("http://localhost/PIDEVVV/getXmlfGouv.php");
                System.out.println("Exc" + hc);
                DataInputStream dis = new DataInputStream(hc.openDataInputStream());

                parser.parse(dis, vil);


                // display the resul
                String[] items1 = new String[vil.getville().length];
                for (int i = 0; i < vil.getville().length; i++) {
                    items1[i] = vil.getville()[i].getGouv();
                }
                gouv = new ChoiceGroup("Entrer le lieu du gouv ", Choice.POPUP, items1, null);



            } catch (Exception e) {
                System.out.println("Exception:" + e.toString());
            }
        } else if (x == false) {
            if (no.getString().length() == 0 && dele.getString().length() == 0 && garde.isSelected(0) == false) {
                System.out.println("ccc");
                try {

                    PharmacieHanlder pharmaciehan = new PharmacieHanlder();


                    // get a parser object
                    SAXParser parser = SAXParserFactory.newInstance().newSAXParser();

                    // get an InputStream from somewhere (could be HttpConnection, for example)
                    HttpConnection hc = (HttpConnection) Connector.open("http://localhost/PIDEVVV/getXmlService.php");
                    System.out.println("Exc" + hc);
                    DataInputStream dis = new DataInputStream(hc.openDataInputStream());

                    parser.parse(dis, pharmaciehan);

                    // display the result
                    pharmacies = pharmaciehan.getPharmacie();
                    String etat;
                    String type = null;
                    System.out.println("Exc" + pharmacies.length);
                    if (pharmacies.length > 0) {
                        for (i = 0; i < pharmacies.length; i++) {

//                    if (no.getString().length() == 0 && gouv.getString().length() == 0 && dele.getString().length() == 0 && cgGender.getSelectedIndex() == 0) {



//                        if (no.getString().toUpperCase().equals(pharmacies[i].getNom().toUpperCase())) {

                            lst.append(pharmacies[i].getNom() + "", null);

                        }
                    }
                } catch (Exception e) {
                    System.out.println("Exception:" + e.toString());
                }
            } else if (no.getString().length() != 0 && cgGender.getSelectedIndex() == 0 && dele.getString().length() == 0) {

                System.out.println("bbbb");
                try {

                    PharmacieHanlder pharmaciehan2 = new PharmacieHanlder();


                    // get a parser object
                    SAXParser parser2 = SAXParserFactory.newInstance().newSAXParser();

                    // get an InputStream from somewhere (could be HttpConnection, for example)
                    HttpConnection hc2 = (HttpConnection) Connector.open("http://localhost/PIDEVVV/getXmlService.php");
                    System.out.println("Exc" + hc2);
                    DataInputStream dis2 = new DataInputStream(hc2.openDataInputStream());

                    parser2.parse(dis2, pharmaciehan2);

                    // display the result
                    pharmacies = pharmaciehan2.getPharmacie();
                    String etat;
                    String type = null;
                    System.out.println("Exc" + pharmacies.length);
                    if (pharmacies.length > 0) {
                        for (int i = 0; i < pharmacies.length; i++) {



                            if (no.getString().toUpperCase().equals(pharmacies[i].getNom().toUpperCase())) {

                                lst.append(pharmacies[i].getNom() + "", null);

                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Exception:" + e.toString());
                }
            } else if (no.getString().length() == 0 && dele.getString().length() != 0 && garde.isSelected(0) == false) {
                System.out.println("aaaa");
                for (int j = 0; j < gouv.size(); j++) {
                    if (gouv.isSelected(j)) {
                        System.out.println("gouv1" + gouv.getString(j));
                    }
                }
                //String p2 = new String(gouv.getString(gouv.getSelectedIndex()));

                try {

                    PharmacieHanlder pharmaciehan2 = new PharmacieHanlder();


                    // get a parser object
                    SAXParser parser2 = SAXParserFactory.newInstance().newSAXParser();

                    // get an InputStream from somewhere (could be HttpConnection, for example)
                    HttpConnection hc2 = (HttpConnection) Connector.open("http://localhost/PIDEVVV/getXmlService.php");
                    System.out.println("Exc" + hc2);
                    DataInputStream dis2 = new DataInputStream(hc2.openDataInputStream());

                    parser2.parse(dis2, pharmaciehan2);

                    // display the result
                    pharmacies = pharmaciehan2.getPharmacie();
                    String etat;
                    String type = null;
                    System.out.println("Exc" + pharmacies.length);
                    if (pharmacies.length > 0) {
                        for (int i = 0; i < pharmacies.length; i++) {


                            if (cgGender.getSelectedIndex() == 0) {
                                String p2 = new String(gouv.getString(gouv.getSelectedIndex()));

                                System.out.println("et" + p2);

                                String p = new String(cgGender.getString(cgGender.getSelectedIndex()));
                                System.out.println("tye" + p);
                                if (p2.toUpperCase().equals(pharmacies[i].getGouv().toUpperCase()) && dele.getString().toUpperCase().equals(pharmacies[i].getDeleg().toUpperCase()) && p.toUpperCase().equals(pharmacies[i].getType().toUpperCase())) {

                                    lst.append(pharmacies[i].getNom() + "", null);
                                    System.out.println("Exception" + i);

                                }

                            }
                            if (cgGender.getSelectedIndex() == 1) {
                                String p2 = new String(gouv.getString(gouv.getSelectedIndex()));

                                System.out.println("et" + p2);

                                String p = new String(cgGender.getString(cgGender.getSelectedIndex()));
                                System.out.println("tye" + p);
                                if (p2.toUpperCase().equals(pharmacies[i].getGouv().toUpperCase()) && dele.getString().toUpperCase().equals(pharmacies[i].getDeleg().toUpperCase()) && p.toUpperCase().equals(pharmacies[i].getType().toUpperCase())) {

                                    lst.append(pharmacies[i].getNom() + "", null);
                                    System.out.println("Exception" + i);

                                }

                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Exception:" + e.toString());
                }
            } else if (garde.isSelected(0) || dele.getString().length() != 0) {
                System.out.println("ddd");
                for (int j = 0; j < gouv.size(); j++) {
                    if (gouv.isSelected(j)) {
                        System.out.println("gouv1" + gouv.getString(j));
                    }
                }

                try {   // this will handle our XML


                    PharmacieHanlder pharmaciehan1 = new PharmacieHanlder();


                    // get a parser object
                    SAXParser parser1 = SAXParserFactory.newInstance().newSAXParser();
                    // get an InputStream from somewhere (could be HttpConnection, for example)
                    HttpConnection hc1 = (HttpConnection) Connector.open("http://localhost/PIDEVVV/getXmlGarde.php");
                    System.out.println("Exc" + hc1);
                    DataInputStream dis1 = new DataInputStream(hc1.openDataInputStream());

                    parser1.parse(dis1, pharmaciehan1);

                    // display the result
                    pharmacies = pharmaciehan1.getPharmacie();

                    System.out.println("Exc" + pharmacies.length);
                    if (pharmacies.length > 0) {
                        for (i = 0; i < pharmacies.length; i++) {
                            // if (no.getString().length() == 0 && gouv.getString().length() == 0 && dele.getString().length() == 0 && garde.getSelectedIndex() == 0) {
                            // String p1 = new String(garde.getString(garde.getSelectedIndex()));

                            //System.out.println("et" + p1);
                            String p2 = new String(gouv.getString(gouv.getSelectedIndex()));

                            if (p2.toUpperCase().equals(pharmacies[i].getGouv().toUpperCase()) && dele.getString().toUpperCase().equals(pharmacies[i].getDeleg().toUpperCase())) {
                                {
                                    lst.append(pharmacies[i].getNom() + "", null);
                                    System.out.println("Exception" + pharmacies[i].getJours());



                                }
                            }
                        }
                    }


                } catch (Exception e) {
                    System.out.println("Exception:" + e.toString());
                }
            }
            m.disp.setCurrent(lst);
            System.out.println("Exception" + lst.toString());
        }
    }

    private String showPharma(String str) {
        String res = "vide";
        int i = 0;
        Pharmacie ph;
        if (pharmacies.length > 0) {
            for (i = 0; i < pharmacies.length; i++) {
                if (pharmacies[i].getNom().equals(str)) {
                    ph = pharmacies[i];
                    id = pharmacies[i].getId();
                    sb.append(ph.Affiche());
                    sb.append("\n");
                    res = sb.toString();
                    sb = new StringBuffer("");
                    break;
                }
            }
        }
        return res;
    }
}
