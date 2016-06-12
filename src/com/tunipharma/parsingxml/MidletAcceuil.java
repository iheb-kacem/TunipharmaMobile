/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunipharma.parsingxml;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Ticker;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Ferdawes
 */
public class MidletAcceuil extends MIDlet implements CommandListener,Runnable {

    Display disp = Display.getDisplay(this);
    Form f = new Form("Acceuil");
    Form loadingDialog = new Form("Please Wait");
    Pharmacie[] pharmacies;
    List lst = new List("Personnes", List.IMPLICIT);
    Ticker t = new Ticker("Tunipharma");
    Command cmdParse = new Command("Personnes", Command.SCREEN, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
   TextField re=new TextField("Entrer le nom du pharmacie ", null, 30, TextField.ANY);
    Form form1 = new Form("Infos Personne");
    StringBuffer sb = new StringBuffer();
    Form rec = new Form("recherche");
    private String[] examples = {
        "Recherche par nom", "Recherche par lieu", "Recherche la pharmacie la plus proche"
    };
    Command cmdstart = new Command("Start", Command.SCREEN, 0);
    List choix;
    private Screen[] screens;
    Image im;

    /**
     *
     */
    public void startApp() {
        f.setTicker(t);
        try {
            im = Image.createImage("/images/pharmacie.png");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
      form1.addCommand(cmdBack);
       form1.setCommandListener(this);
        f.append(im);
        
        f.addCommand(cmdstart);
        f.setCommandListener(this);
        createList();
        createScreens();
        disp.setCurrent(f);
    }

    /**
     *
     */
    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

   
    public void commandAction(Command c, Displayable d) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (c == cmdstart) {

            disp.setCurrent(choix);
        }
        if (c==cmdParse)
        {
             Thread th = new Thread(this);
            th.start();
        }
         

        if (d == choix) {
            int index = choix.getSelectedIndex();
            disp.setCurrent(screens[index]);
            if (c == List.SELECT_COMMAND) {
              System.out.println("test"+lst.getSelectedIndex());
            form1.append("Informations Personne: \n");
            form1.append(showPersonne(lst.getSelectedIndex()));
           
            disp.setCurrent(form1);
        }
        }
        


    }

    private void createList() {
        choix = new List("Recherche", List.IMPLICIT);
        for (int i = 0; i < examples.length; i++) {
            choix.append(examples[i], null);
        }
        choix.setCommandListener(this);
    }
public void run() {
    try 
    {   // this will handle our XML
        PharmacieHanlder pharmaciehan=new PharmacieHanlder();
          
           
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/PIDEV/getXmlService.php");
            System.out.println("Exc"+hc);
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
               
            parser.parse(dis, pharmaciehan);
            
            // display the result
            pharmacies = pharmaciehan.getPharmacie();
             
         System.out.println("Exc"+pharmacies.length);
            if (pharmacies.length > 0) {
                for (int i = 0; i < pharmacies.length; i++) {
                    if(re.getString().equals(pharmacies[i].getNom()))
                    lst.append(pharmacies[i].getNom()+"", null);
                     System.out.println("Exception"+i);
                }
               
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(lst);
         System.out.println("Exception"+lst.toString());
    }

    private String showPersonne(int i) {
        System.out.println("i"+i);
        String res = "";
        if (pharmacies.length > 0) {
            sb.append("* ");
            sb.append(pharmacies[i].toString());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
    
    private void createScreens() {
        screens = new Screen[examples.length];
        screens[0] = createrechernom();
        screens[1] = createrecherlieu();
        //screens[2] = createrechermap();

    }

    private Form createrechernom() {
        Form form = new Form("Recherche par nom");
        form.append(re);
        List lst = new List("Personnes", List.IMPLICIT);
        lst.setCommandListener(this);
       
        form.addCommand(cmdParse);
        form.setCommandListener(this);
        Ticker t = new Ticker("Tunipharma");
        form.setTicker(t);
        return form;
    }

    private Form createrecherlieu() {
        Form form = new Form("Recherche par lieu");
        form.append(new TextField("Entrer le lieu du pharmacie ", null, 30, TextField.ANY));

        Ticker t = new Ticker("Tunipharma");
        form.setTicker(t);
        return form;

    }
}
