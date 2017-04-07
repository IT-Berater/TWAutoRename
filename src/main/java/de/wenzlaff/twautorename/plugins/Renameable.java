/**
 * 
 * ----------------------------------------
 * 
 * @author Copyright 2004 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 02.03.2004 23:35:03 ----------------------------------------
 * 
 * 
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.plugins;

import java.io.File;
import de.wenzlaff.twautorename.gui.OptionGuiable;

/**
 * Schnittstelle zum umbennenen von Dateien.
 */
public interface Renameable {
	/**
	 * Gibt den Key des Plugin zur�ck. Den Klassenname.
	 * 
	 * @return String den Key des Plugin, der Klassenname
	 */
	String getPluginKey();

	/**
	 * Gibt die Beschreibung des Rename Plugin zur�ck.
	 * 
	 * @return String die Beschreibung des Plugin
	 */
	String getPluginBeschreibung();

	/**
	 * Setzt die Datei die ge�ndert werden soll. Die Quelldatei.
	 * 
	 * @param pvDatei
	 *            die Quelldatei Datei die ge�ndert werden soll.
	 */
	void setDateiname(File pvDatei);

	/**
	 * Gibt den neuen Dateiname zur�ck. Der Zieldatei Name.
	 * 
	 * @return String mit den neuen Zieldatei Name
	 */
	String getZielDateiname();

	/**
	 * Gibt das Zielverzeichnis zur�ck.
	 * 
	 * @return gibt das Ziel Verzeichnis
	 */
	String getZielPfad();

	/**
	 * Gibt die Klasse zur�ck, die die Gui implementiert.
	 * 
	 * @return String der Klasse die die Gui implementiert
	 */
	String getGuiClass();

	/**
	 * Gibt das Gui Panel Objekt zur�ck.
	 * 
	 * @return das Gui Panel Object
	 */
	OptionGuiable getGuiObject();
}
