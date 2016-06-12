/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunipharma.display;

import com.tunipharma.midlet.TuniPharma;
import javax.microedition.lcdui.*;

/**
 *
 * @author Houcem
 */
public class AboutDisplay implements CommandListener {

    private TuniPharma m;
    private final Command REQUEST_RETURN = new Command("Retour", Command.EXIT, 1);

    AboutDisplay(TuniPharma m) {

        this.m = m;
        Display disp = Display.getDisplay(m);
        Form f = new Form("Tunipharma");
        StringItem st1 = new StringItem("Tunispharma est une application mobile pour vous aider à trouver la pharmacie de jour, de nuit ou de garde la plus proche .", null);
        StringItem st2 = new StringItem("     *****************     ", null);
        StringItem st3 = new StringItem("Tunipharma permet de :  ", null);
        StringItem st4 = new StringItem("* Afficher les pharmacies de la Tunisie dan une liste ou sur la carte ", null);
        StringItem st5 = new StringItem("* Afficher les pharmacies les plus proches ", null);
        StringItem st6 = new StringItem("* Visualiser l'itinéraire vers une pharmacie sur la carte ", null);
        StringItem st7 = new StringItem("* Afficher les informations d'une pharmacie ", null);
        StringItem st8 = new StringItem("     *****************     ", null);
        StringItem st9 = new StringItem(" Tunipharma est développée par ", null);
        f.append(st1);
        f.append(st2);
        f.append(st3);
        f.append(st4);
        f.append(st5);
        f.append(st6);
        f.append(st7);
        f.append(st8);
        f.append(st9);
        f.addCommand(REQUEST_RETURN);
        disp.setCurrent(f);


    }

    public void commandAction(Command c, Displayable d) {
        if (c == REQUEST_RETURN) {
            m.disp.setCurrent(new MainDisplay(m));
        }
    }
}
