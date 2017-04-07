/**
 * ----------------------------------------
 * 
 * @author Copyright 2003 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 04.03.2004 22:48:40 ----------------------------------------
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.plugins;

import java.io.File;
import java.util.Map;

/**
 * Fügt den Dateiname ein Prefix hinzu.
 */
public class RenamePrefix extends Plugin {

	public String getPluginBeschreibung() {
		return "Fügt den Dateinamen ein Prefix hinzu";
	}

	public void setDateiname(File pvDatei) {
		super.setDateiname(pvDatei);
		Map lvParam = getGuiObject().getParameter();
		String lvPattern = (String) lvParam.get(Plugin.icTEXTFIELD_1);
		String lvNeuerDateiname = lvPattern + pvDatei.getName();
		ivDateiname = lvNeuerDateiname;
	}

}
