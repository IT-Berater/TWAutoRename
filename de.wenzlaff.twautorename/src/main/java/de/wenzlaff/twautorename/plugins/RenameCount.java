/**
 * ----------------------------------------
 * 
 * @author Copyright 2003 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 06.03.2004 17:12:52 ----------------------------------------
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.plugins;

import java.io.File;
import java.util.Map;
import javax.swing.JOptionPane;

import de.wenzlaff.twautorename.gui.TextCountPanel;

/**
 * Nummeriert die Dateien fortlaufend.
 */
public class RenameCount extends Plugin {

	/** Der Zähler um die Anzahl der Dateinamen zu zählen */
	private long ivCount = 0;

	public String getPluginBeschreibung() {
		return "Nummerierung der Dateinamen im Prefix";
	}

	public void setDateiname(File pvDatei) {
		super.setDateiname(pvDatei);
		Map<String, String> lvParam = getGuiObject().getParameter();
		String lvOffset = lvParam.get(Plugin.icTEXTFIELD_1);
		// hole den Reset Parameter, der wird gesetz beim klick auf Start
		String lvReset = lvParam.get(TextCountPanel.KEY_RESET);
		if (lvReset.equals(TextCountPanel.JA)) {
			lvParam.put(TextCountPanel.KEY_RESET, TextCountPanel.NEIN);
			ivCount = 0;
		}
		long lvOffest = 1;
		try {
			lvOffest = Long.parseLong(lvOffset);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,
					"Bitte eine gültige Zahl eingeben, von wo aus die Nummerierung starten soll.");
			return;
		}
		long lvSumme = lvOffest + ivCount;
		String lvNurNummer = lvParam.get(TextCountPanel.KEY_NUR_NUMMERIERUNG);
		if (lvNurNummer.equals(TextCountPanel.JA)) {
			String lvExt = pvDatei.getName();
			int lvPos = lvExt.indexOf('.');
			if (lvPos == -1) {
				ivDateiname = "" + lvSumme;
			} else {
				ivDateiname = lvSumme + lvExt.substring(lvPos);
			}
		} else {
			ivDateiname = lvSumme + pvDatei.getName();
		}
		ivCount++;
	}

}
