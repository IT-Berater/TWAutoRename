/**
 * ----------------------------------------
 * 
 * @author Copyright 2003 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 04.03.2004 18:35:52 ----------------------------------------
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Die Logging Klasse.
 */
public class TWLog {
	/** Loglevel Information. */
	public static final String LOG_INFORMATION = "I";

	/** Loglevel Fehler. */
	public static final String LOG_FEHLER = "F";

	/** Loglevel Veränderung. */
	public static final String LOG_VERAENDERT = "V";

	/** Default log File Name. */
	private static String icDefaultLogFile = "twautorenamelog.txt";

	/**
	 * Schreibt einen Logeintrag.
	 * 
	 * @param pvLevel
	 *            der Level.
	 * @param pvLogMessage
	 *            die Nachricht die geloggt wird.
	 */
	public static void write(String pvLevel, String pvLogMessage) {
		write(pvLevel, icDefaultLogFile, pvLogMessage);
	}

	/**
	 * Schreibt die Loggingnachricht.
	 * 
	 * @param pvLevel
	 *            der Loglevel
	 * @param pvFileName
	 *            der Logging Dateiname.
	 * @param pvLogMessage
	 *            die Loggingnachricht.
	 */
	public static void write(String pvLevel, String pvFileName, String pvLogMessage) {
		TimeZone lvTz = TimeZone.getDefault();
		Date lvDate = new Date();
		DateFormat lvDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		lvDateFormat.setTimeZone(lvTz);
		String lvCurrentTime = lvDateFormat.format(lvDate);
		FileWriter lvWriter = null;
		try {
			File lvDir = new File(".");
			String icLogPfad = lvDir.getCanonicalPath() + System.getProperty("file.separator") + pvFileName;
			lvWriter = new FileWriter(icLogPfad, true);
			String lvLogEintrag = lvCurrentTime + " " + pvLevel + " " + pvLogMessage
					+ System.getProperty("line.separator");
			System.out.println("Log: " + lvLogEintrag);
			lvWriter.write(lvLogEintrag);
			lvWriter.flush();
			lvWriter.close();
		} catch (IOException e) {
			System.out.println("Logfile Fehler in " + icDefaultLogFile + " " + e.getMessage());
		}
	}

	/**
	 * Gibt den Dateinamen des Logfiles zurück.
	 * 
	 * @return String Dateiname des Logfiles
	 */
	public static String getDefaultLogFile() {
		return icDefaultLogFile;
	}

	/**
	 * Löscht das Loggingfile
	 */
	public static void deleteLogFile() {
		FileWriter lvWriter = null;
		try {
			File lvDir = new File(".");
			String icLogPfad = lvDir.getCanonicalPath() + System.getProperty("file.separator") + getDefaultLogFile();
			lvWriter = new FileWriter(icLogPfad);
			lvWriter.flush();
			lvWriter.close();
		} catch (IOException e) {
			System.out.println("Logfile Fehler beim löschen in " + icDefaultLogFile + " " + e.getMessage());
		}
	}
}
