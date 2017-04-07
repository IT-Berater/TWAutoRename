/**
 * ----------------------------------------
 * 
 * @author Copyright 2003 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 20.03.2004 16:52:01 ----------------------------------------
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.plugins;

import java.io.File;

/**
 * Die Klasse für das Löschen der Dateiextension.
 */
public class RenameDeletExt extends Plugin {

	public String getPluginBeschreibung() {
		return "Löscht die Dateinamen Extension";
	}

	public void setDateiname(File pvDatei) {
		super.setDateiname(pvDatei);

		String lvNeuerDateiname = pvDatei.getName();
		int lvPos = lvNeuerDateiname.lastIndexOf('.');
		if (lvPos != -1) {
			lvNeuerDateiname = lvNeuerDateiname.substring(0, lvPos);
			ivDateiname = lvNeuerDateiname;
		} else {
			ivDateiname = lvNeuerDateiname;
		}
	}

}
