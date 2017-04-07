/**
 * 
 * ----------------------------------------
 * 
 * @author Copyright 2004 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 02.03.2004 23:35:17 ----------------------------------------
 * 
 * 
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.gui;

import java.util.Map;
import javax.swing.JPanel;

/**
 * Die Schnittstelle zwischen Gui und Rename Klasse.
 */
public interface OptionGuiable {
	/**
	 * Gibt die Gui Component zurück.
	 * 
	 * @return JComponent
	 */
	JPanel getOptionsPanel();

	/**
	 * Gibt alle nötigen Parameter der Gui zurück.
	 * 
	 * @return Map aller Parameter der Gui
	 */
	Map<String, String> getParameter();

	/**
	 * Übernimmt alle Werte aus der Gui wie Textfelder usw. Wird nur nach dem
	 * klick auf Start aufgerufen.
	 */
	void update();
}
