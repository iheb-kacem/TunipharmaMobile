/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunipharma.midlet;

import com.tunipharma.display.MainDisplay;
import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

/**
 * @author KINGMOEZHAJ
 */
public class TuniPharma extends MIDlet {

    public Display disp = Display.getDisplay(this);
    Image mImage;
    Alert al = new Alert("Spalsh");

    public void startApp() {
        try {
            mImage = Image.createImage("/images/splash.jpg");

        } catch (IOException e) {
            e.printStackTrace();
        }
        al.setImage(mImage);
        al.setTimeout(3000);
        disp.setCurrent(al, new MainDisplay(this));
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
