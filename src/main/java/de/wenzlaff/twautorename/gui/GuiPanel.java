/**
 * ----------------------------------------
 * 
 * @author Copyright 2003 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 04.03.2004 18:49:50 ----------------------------------------
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.gui;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 * Die Root Klasse aller Options Guis.
 */
public abstract class GuiPanel extends JPanel implements OptionGuiable {
	
	public static final String NEIN = "NEIN";

	public static final String JA = "JA";

	/** Die Parameter aller zusätzlichen Gui Elemente */
	protected Map<String, String> ivMap;

	/**
	 * Konstruktor.
	 * 
	 */
	public GuiPanel() {
		super();
		ivMap = new HashMap<String, String>();
	}

	public JPanel getOptionsPanel() {
		return this;
	}

	/**
	 * @see de.wenzlaff.twautorename.gui.OptionGuiable#getParameter()
	 */
	public Map<String, String> getParameter() {
		return ivMap;
	}

	/**
	 * @see de.wenzlaff.twautorename.gui.OptionGuiable#update()
	 */
	public void update() {
	}
}
