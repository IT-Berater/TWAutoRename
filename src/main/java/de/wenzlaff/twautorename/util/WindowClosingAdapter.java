/**
 * ----------------------------------------
 * 
 * @author Copyright 2003 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 04.03.2004 22:33:41 ----------------------------------------
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.util;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Zum schlieﬂen aller Frames und Fenster.
 */
public class WindowClosingAdapter extends WindowAdapter {
	private boolean exitSystem;

	/**
	 * Erzeugt einen WindowClosingAdapter zum Schliessen des Fensters. Ist
	 * exitSystem true, wird das komplette Programm beendet.
	 */
	public WindowClosingAdapter(boolean exitSystem) {
		this.exitSystem = exitSystem;
	}

	/**
	 * Erzeugt einen WindowClosingAdapter zum Schliessen des Fensters. Das
	 * Programm wird nicht beendet.
	 */
	public WindowClosingAdapter() {
		this(false);
	}

	/**
	 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	public void windowClosing(WindowEvent event) {
		event.getWindow().setVisible(false);
		event.getWindow().dispose();
		if (exitSystem) {
			TWLog.write(TWLog.LOG_INFORMATION, "Programm ENDE");
			System.exit(0);
		}
	}
}
