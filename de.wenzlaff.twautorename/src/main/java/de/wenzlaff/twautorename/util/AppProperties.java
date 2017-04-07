/**
 * 
 * ----------------------------------------
 * 
 * @author Copyright 2004 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 02.03.2004 23:38:55 ----------------------------------------
 * 
 * 
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Die Systemweiten Einstellungen als singelton Pattern.
 */
public class AppProperties extends Properties {

	private static final long serialVersionUID = 3257288024025937201L;

	/** Die Properties. */
	private static AppProperties icProperties = null;

	/** Das Resourcen Bundle für die Sprach Einstellung */
	private static ResourceBundle ivCurrentResource;

	/**
	 * Konstruktor private da Singelton, und keine Instanz angelegt werden soll ausser über getInstance.
	 */
	private AppProperties() {
	}

	/**
	 * Gibt die einzige Instanz der Anwendung.
	 * 
	 * @return AppProperties
	 */
	public static AppProperties getInstance() {
		if (icProperties == null) {
			icProperties = new AppProperties();
			ivCurrentResource = ResourceBundle.getBundle("de.wenzlaff.twautorename.util.ProgramProperties");
		}
		return icProperties;
	}

	/**
	 * Gibt eine HashMap aller Klassen zurück die in den Resourcen kein _ enthalten. Das sind die Klassen die Dynamisch angelegt werden samt ihren Guis. Der Klassenname ist der Key
	 * für die Klassen die angelegt werden sollen.
	 * 
	 * @return Map aller Plugins
	 */
	public Map getKlassen() {
		HashMap<String, String> lvEint = new HashMap<String, String>();
		Enumeration lvEnum = ivCurrentResource.getKeys();
		while (lvEnum.hasMoreElements()) {
			String lvKey = (String) lvEnum.nextElement();
			if (lvKey.indexOf('_') == -1) {
				lvEint.put(lvKey, ivCurrentResource.getString(lvKey));
			}
		}
		return lvEint;
	}

	/**
	 * Gibt die Properties für den Key zurück.
	 * 
	 * @param pvKlasse
	 *            den Key
	 * @return String der Value
	 */
	public String getProperties(String pvKlasse) {
		return ivCurrentResource.getString(pvKlasse);
	}
}
