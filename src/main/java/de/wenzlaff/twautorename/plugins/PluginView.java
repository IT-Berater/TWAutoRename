/**
 * @author Copyright 2004 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 25.03.2004 18:47:21
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.plugins;

/**
 * Die View für die Plugins.
 */
public class PluginView {

	/** Die Refernenz auf die Factory. */
	private Renameable ivFactory;

	/**
	 * Konstruktor.
	 * 
	 * @param pvFactory
	 *            Renameable
	 */
	public PluginView(Renameable pvFactory) {
		ivFactory = pvFactory;
	}

	/**
	 * Gibt die Plugin Beschreibung.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return ivFactory.getPluginBeschreibung();
	}

	/**
	 * Gibt die Gui Klasse des angezeigten Plugins.
	 * 
	 * @return String den Gui Klassennamen
	 */
	public String getGuiClass() {
		return ivFactory.getGuiClass();
	}

	/**
	 * Gibt den Key d.h. den Klassennamen der Renam Klasse.
	 * 
	 * @return den Key der Rename Klasse
	 */
	public String getKey() {
		return ivFactory.getPluginKey();
	}

	/**
	 * Gibt die Factory.
	 * 
	 * @return gibt die Factory die mit der View verbunden ist
	 */
	public Renameable getFactory() {
		return ivFactory;
	}
}
