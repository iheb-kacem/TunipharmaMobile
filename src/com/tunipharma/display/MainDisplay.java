/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunipharma.display;

import com.tunipharma.midlet.TuniPharma;
import com.tunipharma.parsingxml.DisplayRecherche;
import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author KINGMOEZHAJ
 */
public class MainDisplay extends Canvas{

    int w = getWidth();
    int h = getHeight();
    Image Entete;
    Image carte;
    Image recherche;
    Image approximite;
    Image about;
    int menu = 1;
    TuniPharma m;

    public MainDisplay(TuniPharma m) {
        this.m = m;
        try {
            Entete = Image.createImage("/images/entete.jpg");
            carte = Image.createImage("/images/carteclk.jpg");
            recherche = Image.createImage("/images/recherche.jpg");
            approximite = Image.createImage("/images/approximite.jpg");
            about = Image.createImage("/images/about.jpg");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected void paint(Graphics g) {

        g.setColor(193, 255, 193);
        g.fillRect(0, 0, w, h);
        g.drawImage(Entete, 0, 0, g.TOP | g.LEFT);
        g.drawImage(carte, (w / 2) - 5, (h / 2) - 5, g.BOTTOM | g.RIGHT);
        g.drawImage(recherche, (w / 2) + 5, (h / 2) - 5, g.BOTTOM | g.LEFT);
        g.drawImage(approximite, w / 2 - 5, h / 2 + 5, g.TOP | g.RIGHT);
        g.drawImage(about, w / 2 + 5, h / 2 + 5, g.TOP | g.LEFT);
    }

    public void initImages() {
        switch (menu) {
            case 1:
                try {
                    carte = Image.createImage("/images/carteclk.jpg");
                    recherche = Image.createImage("/images/recherche.jpg");
                    approximite = Image.createImage("/images/approximite.jpg");
                    about = Image.createImage("/images/about.jpg");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case 2:
                try {
                    carte = Image.createImage("/images/carte.jpg");
                    recherche = Image.createImage("/images/rechercheclk.jpg");
                    approximite = Image.createImage("/images/approximite.jpg");
                    about = Image.createImage("/images/about.jpg");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case 3:
                try {
                    carte = Image.createImage("/images/carte.jpg");
                    recherche = Image.createImage("/images/recherche.jpg");
                    approximite = Image.createImage("/images/approximiteclk.jpg");
                    about = Image.createImage("/images/about.jpg");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case 4:
                try {
                    carte = Image.createImage("/images/carte.jpg");
                    recherche = Image.createImage("/images/recherche.jpg");
                    approximite = Image.createImage("/images/approximite.jpg");
                    about = Image.createImage("/images/aboutclk.jpg");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

    protected void keyPressed(int keyCode) {
        super.keyPressed(keyCode); //To change body of generated methods, choose Tools | Templates.
        switch (getGameAction(keyCode)) {
            case UP:
                if (menu > 2) {
                    menu = menu - 2;
                    initImages();
                }
                break;
            case DOWN:
                if (menu <= 2) {
                    menu = menu + 2;
                    initImages();
                }
                break;
            case LEFT:
                if (menu % 2 == 0) {
                    menu = menu - 1;
                    initImages();
                }
                break;
            case RIGHT:
                if (menu % 2 == 1) {
                    menu = menu + 1;
                    initImages();
                }
                break;
            case FIRE:
                if (menu == 1) {
                    new MapDisplay(m);
                }
                if (menu == 3) {
                    new PlusProcheDisplay(m);
                }
                if (menu == 2) {
                    new DisplayRecherche(m);
                }
                if (menu == 4) {
                    new AboutDisplay(m);
                }
                break;
        }
        repaint();
    }
}
