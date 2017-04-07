/**
 * ----------------------------------------
 * @author Copyright 2003 by Thomas Wenzlaff (<a href="mailto://java@wenzlaff.de">java@wenzlaff.de</a>) 
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de</a>
 * @since 20.03.2004 16:49:48
 * ----------------------------------------
 * 
 * Source is only for non commercial and coaching usage.
 * Not Warranty to use it.
 */

package de.wenzlaff.twautorename.gui;

import de.wenzlaff.twautorename.util.AppProperties;

/**
 * Löscht die Extension.
 */
public class DeletExtPanel extends TextFieldPanel {

	private static final long serialVersionUID = 3689635774358238521L;

	private static final String icCLASSNAME = "de.wenzlaff.twautorename.gui.DeletExtPanel";

	/**
	 * Konstruktor.
	 */
	public DeletExtPanel() {
		ivTextArea.setText(AppProperties.getInstance().getProperties(icCLASSNAME + HILFE_TEXT_TEXTAREA));
	}
}
