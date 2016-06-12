/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunipharma.display;

import com.nokia.maps.Base;
import com.nokia.maps.MapCanvasExample;
import com.nokia.maps.common.GeoCoordinate;
import com.nokia.maps.component.feedback.FocalEventListener;
import com.nokia.maps.component.feedback.FocalObserverComponent;
import com.nokia.maps.map.MapCanvas;
import com.nokia.maps.map.MapDisplayState;
import com.nokia.maps.map.MapStandardMarker;
import com.tunipharma.midlet.TuniPharma;
import java.io.DataInputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import com.tunipharma.parsingxml.*;

/**
 *
 * @author KINGMOEZHAJ
 */
public class MapDisplay {

    private TuniPharma m;
    private Display display;
    private MapCanvas demo;
    
    public MapDisplay(TuniPharma m) {
        this.m = m;
        Base.InitialiseAuth();
        if (Base.checkAuth(m)) {
            Display display = Display.getDisplay(m);
            demo = new MapDemo(display, m);
            display.setCurrent(demo);
        }

    }

    private class MapDemo extends MapCanvasExample implements FocalEventListener, Runnable, CommandListener {

        CoordonneesPharma[] coordonnees = new CoordonneesPharma[100];
        private final FocalObserverComponent focalObserver;
        private final Command REQUEST_RETURN = new Command("Retour", Command.EXIT, 1);
        private final Command REQUEST_ENTER = new Command("Entrer", Command.OK, 1);
        private CoordonneesPharma pharmacieOnFocus;

        public MapDemo(Display display, MIDlet midlet) {
            super(display, midlet);
            focalObserver = new FocalObserverComponent(this);
            map.addMapComponent(focalObserver);
            map.setState(new MapDisplayState(new GeoCoordinate(34.839580, 9.680922, 0), 7));
            addCommand(REQUEST_RETURN);
            
            setCommandListener(this);
            pharmacieOnFocus = null;
            Thread th = new Thread(this);
            th.start();
        }

        public void run() {
            try {
                // this will handle our XML
                CoordonneesPharmaHandler coordonnee = new CoordonneesPharmaHandler();
                // get a parser object
                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                // get an InputStream from somewhere (could be HttpConnection, for example)
                HttpConnection hc = (HttpConnection) Connector.open("http://localhost/coordonnees/getXmlCoordonnees.php");
                DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                parser.parse(dis, coordonnee);
                // display the result
                coordonnees = coordonnee.getCoordonneesPharma();
                if (coordonnees.length > 0) {
                    for (int i = 0; i < coordonnees.length; i++) {
                        double x = coordonnees[i].getLat();
                        double y = coordonnees[i].getLog();
                        CoordonneesPharma ph = coordonnees[i];
                        addFocsableMarker(new GeoCoordinate(x, y, 0), ph);
                    }
                }

            } catch (Exception e) {
                System.out.println("Exception:" + e.toString());
            }

        }

        private void addFocsableMarker(GeoCoordinate coord, CoordonneesPharma ph) {
            MapStandardMarker marker = mapFactory.createStandardMarker(coord);
            focalObserver.addData(marker, ph);
            map.addMapObject(marker);
        }

        public void onFocusChanged(Object focus) {
            if ((focus != null) && (map.getZoomLevel() > 10)) {
                pharmacieOnFocus = (CoordonneesPharma) focus;
                progressStart("Pharmacie " + pharmacieOnFocus.getNom(), "");
                addCommand(REQUEST_ENTER);
            } else {
                removeCommand(REQUEST_ENTER);
                pharmacieOnFocus = null;
                progressEnd();
            }
        }

        public void commandAction(Command c, Displayable d) {
            if (c == REQUEST_RETURN) {
                m.disp.setCurrent(new MainDisplay(m));
            }
            if ((c == REQUEST_ENTER) && (pharmacieOnFocus != null)) {
                Alert al = new Alert("afficher détails du pharmacie");
                al.setString("Affichage des détails de la pharmacie non intégrer a partir du map");
                display.setCurrent(al, demo);

            }
        }
    }
}
