/**
 * 
 * ----------------------------------------
 * 
 * @author Copyright 2007 by Thomas Wenzlaff (<a
 *         href="mailto://gesundheitskarte@wenzlaff.de">gesundheitskarte@wenzlaff.de</a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de</a>
 * @since 01.09.2007
 * @version 1.0 ----------------------------------------
 * 
 * Not Warranty to use it.
 */

package de.wenzlaff.twautorename.plugins;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.regex.PatternSyntaxException;

import de.wenzlaff.twautorename.ExifAdapter;
import de.wenzlaff.twautorename.gui.SuchenErsetzenExifPanel;
import de.wenzlaff.twautorename.gui.TextCountPanel;

/**
 * Macht das Suchen in einem Dateinamen und das Ersetzen durch Exif Infos.
 */
public class RenameSuchenErsetzenExif extends Plugin {

	/** Fehlertext. */
	private static final String SUCHE_NACHRICHT = "Bitte einen g�ltigen regul�ren Pattern f�r die Suche eingeben.";

	@Override
	public String getPluginBeschreibung() {
		return "Suchen und Ersetzen durch versteckte Exif Dateiinformation im Bild";
	}

	@Override
	public void setDateiname(File pvDatei) {
		super.setDateiname(pvDatei);
		Map lvParam = getGuiObject().getParameter();
		String lvSuchenPattern = (String) lvParam.get(SuchenErsetzenExifPanel.TEXTFIELD_SUCHEN);

		if (lvSuchenPattern.length() <= 0) {
			showMessage(SUCHE_NACHRICHT + " Eingabe:" + lvSuchenPattern);
			return;

		}
		try {
			String lvErsetzenPattern = (String) lvParam.get(SuchenErsetzenExifPanel.TEXTFIELD_ERSETZEN);
			String lvDateiname = pvDatei.getName();

			int lvErsteKlammer = lvErsetzenPattern.indexOf("[");
			if (lvErsteKlammer == -1) {
				showMessage("Keine �ffnende Klammer [ gefunden im ersetzen Pattern: " + lvErsetzenPattern);
				return;
			}
			int lvLetzteKlammer = lvErsetzenPattern.indexOf("]");
			if (lvLetzteKlammer == -1) {
				showMessage("Keine schlie�ende Klammer ] gefunden im ersetzen Pattern: " + lvErsetzenPattern);
				return;
			}
			String lvPraefix = lvErsetzenPattern.substring(0, lvErsteKlammer);
			String lvPostfix = lvErsetzenPattern.substring(lvLetzteKlammer + 1, lvErsetzenPattern.length());
			String lvMakro = lvErsetzenPattern.substring(lvErsteKlammer, lvLetzteKlammer + 1);

			Map<String, String> lvExif = ExifAdapter.getMetaDataMap(pvDatei);
			String lvExifInhalt = lvExif.get(lvMakro);

			lvExifInhalt = lvExifInhalt.replaceAll(":", ".");
			lvExifInhalt = lvExifInhalt.replaceAll(" ", "-");
			lvExifInhalt = lvExifInhalt.replaceAll("/", "-");
			lvExifInhalt = lvExifInhalt.replace('(', '-');
			lvExifInhalt = lvExifInhalt.replace(')', '-');

			ivDateiname = lvDateiname.replaceAll(lvSuchenPattern, lvPraefix + lvExifInhalt + lvPostfix);

			makeExifDatei(lvParam, pvDatei, lvExif);

		} catch (NullPointerException pvException) {
			showMessage(SUCHE_NACHRICHT + " Fehler: " + pvException.getLocalizedMessage());
			return;

		} catch (PatternSyntaxException pvException) {
			showMessage(SUCHE_NACHRICHT + " Fehler: " + pvException.getLocalizedMessage());
			return;
		} catch (IOException e) {
			showMessage(SUCHE_NACHRICHT + " Fehler: " + e.getLocalizedMessage());
			return;
		}
	}

	private void makeExifDatei(final Map pvExifDaten, final File pvDatei, Map<String, String> pvLvExif) {
		String lvNurNummer = (String) pvExifDaten.get(SuchenErsetzenExifPanel.KEY_EXIF_DATEI);

		if (lvNurNummer.equals(TextCountPanel.JA)) {
			final String lvExifDateiname = pvDatei.getAbsolutePath() + ".Exif.txt";
			final File lvExifFile = new File(lvExifDateiname);
			System.out.println("Schreibe Exif Daten in Datei: " + lvExifDateiname);
			FileOutputStream lvOs = null;

			try {
				lvOs = new FileOutputStream(lvExifFile);

				Set<String> lvKeySet = pvLvExif.keySet();
				for (String lvKey : lvKeySet) {
					String lvValue = pvLvExif.get(lvKey);
					try {
						lvOs.write(lvKey.getBytes());
						lvOs.write("\t ".getBytes());
						lvOs.write(lvValue.getBytes());
						lvOs.write("\r\n".getBytes());
					} catch (IOException pvException) {
					}
				}
			} catch (FileNotFoundException pvException1) {

			} finally {
				if (lvOs != null)
					try {
						lvOs.close();
					} catch (IOException e) {
					}
			}
		}
	}

}
