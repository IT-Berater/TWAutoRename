/**
 * 
 * ----------------------------------------
 * 
 * @author Copyright 2004 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 02.03.2004 23:37:56 ----------------------------------------
 * 
 * 
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.plugins;

import java.io.File;

/**
 * �ndert alles in Kleinbuchstaben und f�gt ein .KLEIN hinzu.
 */
public class RenameKlein extends Plugin {
	/** Diese Dateiextension wir dem Dateinamen angehangen. */
	private static final String icExtension = ".KLEIN";

	public void setDateiname(File pvDatei) {
		super.setDateiname(pvDatei);
		ivDateiname = pvDatei.getName().toLowerCase() + icExtension;
	}

	public String getPluginBeschreibung() {
		return "�ndert die Dateinamen in Kleinbuchstaben und f�gt Extension .KLEIN hinzu";
	}
}
