/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunipharma.service;

import com.tunipharma.midlet.TuniPharma;
import com.tunipharma.parsingxml.DisplayRecherche;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Spacer;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author PLAYER9
 */
public class DisplayService implements Runnable, CommandListener {

    //Display disp = Display.getDisplay(this);
    Service[] services;
    int id;
    String fb_id;
    String rep = "";
    Form f = new Form("Authentification");
    TextField tmail = new TextField("Email/Téléphone", null, 50, TextField.ANY);
    TextField tpasswd = new TextField("Mot de passe", null, 50, TextField.PASSWORD);
    TextField tcom = new TextField("Commentaire", null, 300, TextField.ANY);
    Command CmVerif = new Command("Check", Command.SCREEN, 0);
    Command CmComm = new Command("Send", Command.SCREEN, 0);
    Command CmBack_Service = new Command("Retour", Command.EXIT, 0);
    ImageItem imgItem;
    Alert alt = new Alert("ERROR", "Connexion échouée !", null, AlertType.ERROR);
    TuniPharma m;

    public DisplayService(TuniPharma m, int id) {
        this.m = m;
        this.id=id;
        Image img;
        try {
            img = Image.createImage("/images/fb.png");
            imgItem = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        f.append(imgItem);
        f.append(new Spacer(10, 10));
        f.append(tmail);
        f.append(tpasswd);
        f.addCommand(CmVerif);
        f.addCommand(CmBack_Service);
        f.setCommandListener(this);

        //  disp.setCurrent(lst);
        m.disp.setCurrent(new Draw());

    }

    public void run() {
        HttpConnection hc;
        DataInputStream dis;
        int ch;
        StringBuffer sb = new StringBuffer("");
        try {
            hc = (HttpConnection) Connector.open("http://localhost/fb_profile_conx/fn.php?mail=" + tmail.getString() + "&pwd=" + tpasswd.getString());
            dis = hc.openDataInputStream();
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
            rep = sb.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (rep.indexOf("success") != -1) {
            fb_id = rep.substring(rep.indexOf("success") + 8, (rep.length()) - 1);
            new DisplayCommentaire(m, id, fb_id);
        } else {
            m.disp.setCurrent(alt, f);
        }
    }

    public void commandAction(Command c, Displayable d) {
        if (c == CmVerif) {

            Thread th = new Thread(this);
            th.start();
            m.disp.setCurrent(new DrawWait());
        }
        
        if (c == CmComm) {

            Thread th = new Thread(this);
            th.start();
            m.disp.setCurrent(new DrawWait());
        }

        if (c == CmBack_Service) {
            m.disp.setCurrent(new Draw());
        }

        if (c == CmBack_Service) {
            tmail.setString("");
            tpasswd.setString("");
            m.disp.setCurrent(f);
        }
    }

    public class Draw extends Canvas implements CommandListener, Runnable {

        int w = getWidth();
        int h = getHeight();
        int x;
        int y;
        Command CmComment = new Command("Commenter", Command.OK, 0);
        Command CmBack_Info = new Command("Retour", Command.BACK, 0);

        public Draw() {
            this.addCommand(CmComment);
            this.addCommand(CmBack_Info);
            this.setCommandListener(this);
            Thread tt = new Thread(this);
            tt.start();
        }

        protected void paint(Graphics g) {
            g.setColor(255, 255, 255);
            g.fillRect(0, 0, w, h);

            g.setColor(0xadff2f);
            g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE));

            g.drawString("SERVICES", w / 2, y, g.BASELINE | g.HCENTER);
            y = y + 40;

            if (services.length > 0) {

                for (int i = 0; i < services.length; i++) {
                    int k = 0;
                    boolean b = true;
                    while (k < i) {
                        if (services[i].getCategorie().equals(services[k].getCategorie())) {
                            k = i + 1;
                            b = false;
                        }
                        k = k + 1;
                    }
                    if (b) {
                        x = x - 15;
                        g.setColor(0xad0f2f);
                        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                        g.drawString(services[i].getCategorie(), x, y, g.BASELINE | g.LEFT);
                        g.setColor(0x000000);
                        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_SMALL));
                        x = x + 15;
                        y = y + 20;
                        for (int j = i; j < services.length; j++) {
                            if (services[i].getCategorie().equals(services[j].getCategorie())) {
                                g.drawString(services[j].getNom(), x, y, g.BASELINE | g.LEFT);
                                y = y + 20;
                            }
                        }
                    }
                }

            } else {
                x = x - 14;
                g.setColor(0xad0f2f);
                g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                g.drawString("la pharmacie ne possède aucun service !!!", x, y, g.BASELINE | g.LEFT);
            }
        }

        public void commandAction(Command c, Displayable d) {
            if (c == CmComment) {
                m.disp.setCurrent(f);
            }
            if (c == CmBack_Info){
                //new DisplayRecherche(m);
                m.disp.setCurrent(DisplayRecherche.form);
            }
        }

        public void run() {
            try {
                // this will handle our XML
                ServiceHandler servicesHandler = new ServiceHandler();
                // get a parser object
                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                // get an InputStream from somewhere (could be HttpConnection, for example)
                HttpConnection hc = (HttpConnection) Connector.open("http://localhost/PI/services.php?id=" + id);
                DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                parser.parse(dis, servicesHandler);
                // display the result
                services = servicesHandler.getService();
                /*
                 if (services.length > 0) {
                 for (int i = 0; i < services.length; i++) {
                 lst.append(services[i].getNom(), null);
                 }
                 }
                 */
            } catch (Exception e) {
                System.out.println("Exception:" + e.toString());
            }
            x = 20;
            y = 20;
            repaint();
        }

    }

    public class DrawWait extends Canvas {

        int w = getWidth();
        int h = getHeight();

        public DrawWait() {
        }

        protected void paint(Graphics g) {
            g.setColor(255, 255, 255);
            g.fillRect(0, 0, w, h);
            g.setColor(0, 0, 0);
            g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_SMALL));
            g.drawString("WAIT !!!", w / 2, h / 2, g.BASELINE | g.HCENTER);
        }
    }

}
