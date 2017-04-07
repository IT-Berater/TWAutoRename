/**
 * @author Copyright 2004 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 12.04.2004
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.plugins;

import java.io.File;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

import de.wenzlaff.twautorename.gui.SuchenErsetzenPanel;

/**
 * Macht das Suchen in einem Dateinamen und das Ersetzen.
 */
public class RenameSuchenErsetzen extends Plugin {

	/** Fehlertext. */
	private static final String SUCHE_NACHRICHT = "Bitte einen gültigen regulären Pattern für die Suche eingeben.";

	public String getPluginBeschreibung() {
		return "Suchen und Ersetzen im Dateinamen";
	}

	public void setDateiname(File pvDatei) {
		super.setDateiname(pvDatei);
		Map lvParam = getGuiObject().getParameter();
		String lvSuchenPattern = (String) lvParam.get(SuchenErsetzenPanel.TEXTFIELD_SUCHEN);
		if (lvSuchenPattern.length() == 0) {
			showMessage(SUCHE_NACHRICHT);
			return;

		}
		String lvErsetzenPattern = (String) lvParam.get(SuchenErsetzenPanel.TEXTFIELD_ERSETZEN);
		String lvDateiname = pvDatei.getName();
		try {
			// Läuft nur ab Java 1.5
			// ivDateiname = lvDateiname.replace(lvSuchenPattern,
			// lvErsetzenPattern);
			ivDateiname = lvDateiname.replaceAll(lvSuchenPattern, lvErsetzenPattern);
		} catch (NullPointerException pvException) {
			showMessage(SUCHE_NACHRICHT);
			return;

		} catch (PatternSyntaxException pvException) {
			showMessage(SUCHE_NACHRICHT + " " + pvException.getLocalizedMessage());
			return;
		}
	}

}
