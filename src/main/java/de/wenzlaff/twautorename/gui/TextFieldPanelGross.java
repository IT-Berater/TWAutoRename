/**
 * ----------------------------------------
 * 
 * @author Copyright 2003 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 04.03.2004 19:38:36 ----------------------------------------
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.gui;

import de.wenzlaff.twautorename.util.AppProperties;

/**
 * Alle Dateinamen in Grossbuchstaben.
 */
public class TextFieldPanelGross extends TextFieldPanel {
	private static final long serialVersionUID = 3257848800759592245L;

	private static final String icCLASSNAME = "de.wenzlaff.twautorename.gui.TextFieldPanelGross";

	/**
	 * Konstruktor.
	 */
	public TextFieldPanelGross() {
		ivTextArea.setText(AppProperties.getInstance().getProperties(icCLASSNAME + TextFieldPanel.HILFE_TEXT_TEXTAREA));
	}
}
