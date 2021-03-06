/**
* Copyright (c) 2013 Nokia Corporation.
*/
package com.nokia.maps;


import com.nokia.maps.Base;
import com.nokia.maps.helper.CommandRunner;
import com.nokia.maps.helper.CommandRunner;
import com.nokia.maps.map.GestureMapCanvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;


/**
 * Common functionality needed by all example applications which
 * use the Gesture API.  The Example is based on the GestureMapCanvas
 * from the maps-component.jar
 */
public abstract class GestureMapCanvasExample extends GestureMapCanvas implements CommandRunner {

    private final Base base;

    /**
     * Standard constructor for initialising the screen and setting up
     * the closure of the example.
     * @param display the screen display
     * @param midlet  midlet used for closure
     */
    public GestureMapCanvasExample(Display display, MIDlet midlet) {
        super(display);
        base = new Base(midlet, display, this, this);
    }

    /**
     * Alternate Constructor which forces the map tiles to be a specific'
     * size.
     * @param display the screen display
     * @param midlet midlet used for closure
     * @param resolution the tile resolution, either one of MapDisplay.MAP_RESOLUTION_128_x_128 or MapDisplay.MAP_RESOLUTION_256_x_256
     */
    public GestureMapCanvasExample(Display display, MIDlet midlet, int resolution) {
        super(display, resolution);
        base = new Base(midlet, display, this, this);
    }

    /**
     * Ensure that the Zoom Buttons are at the back of the display queue. e.g.
     * behind the context menu.
     */
    protected void moveZoomButtonToBack() {
        base.moveZoomButtonToBack();
    }

    /**
     * Hides progress dialog
     */
    protected void progressEnd() {
        base.progressEnd();
    }

    /**
     * Shown progress dialog
     *
     * @param note
     *            note to show
     * @param onFail
     *            text shown to user if exception occurs during progress
     */
    protected void progressStart(String note, String onFail) {
        base.progressStart(note, onFail);
    }

    protected void note(String note, int delay) {
        base.note(note, delay);
    }

    /**
     * shows error dialog
     * @param text The text to display.
     */
    protected void error(String text) {
        base.error(text);
    }

    /**
     *
     *
     * @see com.nokia.maps.map.MapCanvas#onMapUpdateError(java.lang.String,
     *      java.lang.Throwable, boolean)
     * @param description
     *            the description of the source of the error
     * @param detail
     *            the exception detail, such as IOException etc
     * @param critical
     *            if this is critical, always true
     */
    public void onMapUpdateError(String description, Throwable detail,
            boolean critical) {
        base.onMapUpdateError(description, detail, critical);
    }

    /**
     * This means that the all tiles are present and completely rendered with
     * all objects present.
     */
    public void onMapContentComplete() {}

    /**
     * This can be overridden to provide extra commands.
     * @param c
     */
    public void commandRun(Command c) {}

    /**
     *
     * @return the actual Command listener for this example.
     */
    public CommandListener getCommandListener() {
        return base;
    }
}
