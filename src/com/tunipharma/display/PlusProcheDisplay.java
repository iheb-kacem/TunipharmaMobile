/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunipharma.display;

import com.nokia.maps.Base;
import com.nokia.maps.MapCanvasExample;
import com.nokia.maps.common.GeoCoordinate;
import com.nokia.maps.map.MapDisplayState;
import com.nokia.maps.map.MapPolyline;
import com.nokia.maps.map.MapStandardMarker;
import com.nokia.maps.routing.Mode;
import com.nokia.maps.routing.Route;
import com.nokia.maps.routing.RouteFactory;
import com.nokia.maps.routing.RouteListener;
import com.nokia.maps.routing.RouteRequest;
import com.nokia.maps.routing.WaypointParameterList;
import com.nokia.maps.routing.enums.RoutingType;
import com.tunipharma.entities.Pharmacie;
import com.tunipharma.midlet.TuniPharma;
import com.tunipharma.parsingxml.PlusProcheHanlder;
import java.io.DataInputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.location.Coordinates;
import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationProvider;
import javax.microedition.midlet.MIDlet;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author KINGMOEZHAJ
 */
public class PlusProcheDisplay {

    private TuniPharma m;

    public PlusProcheDisplay(TuniPharma m) {
        this.m = m;
        Base.InitialiseAuth();
        if (Base.checkAuth(m)) {
            Display display = Display.getDisplay(m);
            RoutingDemo demoRouting = new RoutingDemo(display, m);
            display.setCurrent(demoRouting);
        }

    }

    private class RoutingDemo extends MapCanvasExample implements RouteListener, CommandListener {

        // user selected coordinates
        private final WaypointParameterList wpl = new WaypointParameterList();
        private Mode mode;
        protected Route[] routes; // routing results
        private final Command REQUEST_LOCATION = new Command("Me Localiser", Command.OK, 1);
        private final Command REQUEST_Day = new Command("Plus Proche Pharmacie de Jour", Command.OK, 2);
        private final Command REQUEST_NIGHT = new Command("Plus Proche Pharmacie de Nuit", Command.OK, 3);
        private final Command REQUEST_RETURN = new Command("Retour", Command.EXIT, 1);
        private Thread t;
        private GeoCoordinate user;
        private MapStandardMarker markerUser;
        private Pharmacie plusProche;
        private MapStandardMarker markerPlusProche;

        public RoutingDemo(Display display, MIDlet midlet) {
            // Displaying polylines can require a lot of heap memory.
            // Force the example to use the smaller tile size to reduce the heap requirement.
            super(display, midlet);
            // Set up the map, this will initially display a map of central Berlin
            addCommand(REQUEST_LOCATION);
            addCommand(REQUEST_NIGHT);
            addCommand(REQUEST_Day);
            addCommand(REQUEST_RETURN);
            setCommandListener(this);
            map.setState(new MapDisplayState(new GeoCoordinate(34.839580, 9.680922, 0), 7));
            user = new GeoCoordinate(0, 0, 0);
            markerUser = mapFactory.createStandardMarker(user, 10, null, MapStandardMarker.BALLOON);
            map.addMapObject(markerUser);
            markerPlusProche = mapFactory.createStandardMarker(user);
            map.addMapObject(markerPlusProche);


            LocateMe();
        }

        private void LocateMe() {
            t = new Thread(
                    new Runnable() {
                public void run() {
                    try {
                        wpl.clear();
                        setTicker(null);
                        routes = null;
                        
                        Location l;
                        LocationProvider lp;
                        Coordinates c;
                        Criteria cr = new Criteria();
                        cr.setHorizontalAccuracy(500);
                        lp = LocationProvider.getInstance(cr);
                        l = lp.getLocation(60);
                        c = l.getQualifiedCoordinates();
                        if (c != null) {
                            user.setAltitude(c.getAltitude());
                            user.setLatitude(c.getLatitude());
                            user.setLongitude(c.getLongitude());
                        }
                        markerUser.setCoordinate(user);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            });
            t.start();
        }
        /*
         * (non-Javadoc)
         *
         * @see Base#commandRun(javax.microedition.lcdui.Command)
         */

        public void commandAction(Command c, Displayable d) {
            if (c == REQUEST_Day) {
                t = new Thread(
                        new Runnable() {
                    public void run() {
                        try {
                            wpl.clear();
                            setTicker(null);
                            routes = null;
                            // this will handle our XML
                            PlusProcheHanlder plusProcheHanlder = new PlusProcheHanlder();
                            // get a parser object
                            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                            // get an InputStream from somewhere (could be HttpConnection, for example)
                            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/TuniPharma/plusProche.php?log=" + user.getLongitude() + "&lat=" + user.getLatitude() + "&type=jour");
                            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                            parser.parse(dis, plusProcheHanlder);
                            // display the result
                            plusProche = plusProcheHanlder.getPharmacie();
                            GeoCoordinate coordinatePlusProche = new GeoCoordinate(plusProche.getLocation().getLat(), plusProche.getLocation().getLon(), 0);
                            markerPlusProche.setCoordinate(coordinatePlusProche);
                            wpl.addCoordinate(user);
                            wpl.addCoordinate(coordinatePlusProche);
                            mode = new Mode();
                            mode.setRoutingType(RoutingType.FASTEST);
                            makeRoutingRequest(mode);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                t.start();
            }
            if (c == REQUEST_NIGHT){ 
                t = new Thread(
                        new Runnable() {
                    public void run() {
                        try {
                            wpl.clear();
                            setTicker(null);
                            routes = null;
                            // this will handle our XML
                            PlusProcheHanlder plusProcheHanlder = new PlusProcheHanlder();
                            // get a parser object
                            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                            // get an InputStream from somewhere (could be HttpConnection, for example)
                            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/TuniPharma/plusProche.php?log=" + user.getLongitude() + "&lat=" + user.getLatitude() + "&type=nuit");
                            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                            parser.parse(dis, plusProcheHanlder);
                            // display the result
                            plusProche = plusProcheHanlder.getPharmacie();
                            GeoCoordinate coordinatePlusProche = new GeoCoordinate(plusProche.getLocation().getLat(), plusProche.getLocation().getLon(), 0);
                            markerPlusProche.setCoordinate(coordinatePlusProche);
                            wpl.addCoordinate(user);
                            wpl.addCoordinate(coordinatePlusProche);
                            mode = new Mode();
                            mode.setRoutingType(RoutingType.FASTEST);
                            makeRoutingRequest(mode);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                t.start();
            }
            if (c == REQUEST_LOCATION){
                LocateMe();
            }
            if (c == REQUEST_RETURN) {
                m.disp.setCurrent(new MainDisplay(m));
            }
        }

        /**
         * Executes routing with the given modes.
         *
         * @param modes the routing mode to use.
         */
        private void makeRoutingRequest(Mode modes) {
            progressStart("Calculating route", "Route not available");
            RouteFactory rf = RouteFactory.getInstance();

            rf.createRouteRequest().calculateRoute(wpl, modes, this);
        }

        /**
         * Callback function initiated when a route request has successfully
         * completed.
         *
         * @param request the route request that initiated the service call.
         * @param routes the routes found that fulfill the request.
         */
        public void onRequestComplete(RouteRequest request, Route[] routes) {

            map.removeAllMapObjects();
            this.routes = routes;
            for (int i = 0; i < routes.length; i++) {
                MapPolyline line = mapFactory.createMapPolyline(
                        routes[i].getShape(), 2);

                line.setColor(0xa00000ff);
                map.addMapObject(line);

                map.addMapObject(
                        mapFactory.createStandardMarker(
                        routes[i].getStart().getMappedPosition(), 10,
                        "A", MapStandardMarker.BALLOON));

                map.addMapObject(
                        mapFactory.createStandardMarker(
                        routes[i].getDestination().getMappedPosition(),
                        10, "B", MapStandardMarker.BALLOON));

                map.zoomTo(routes[i].getBoundingBox(), false);
            }
            progressEnd();
        }

        /**
         * Callback function should the Route Request fail for some reason.
         *
         * @param request the initiating route request.
         * @param error the reason the request failed.
         */
        public void onRequestError(RouteRequest request, Throwable error) {
            error(error.toString());
        }
    }
}
