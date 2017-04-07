/**
 * 
 * ----------------------------------------
 * 
 * @author Copyright 2003 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 20.03.2003 20:35:19 ----------------------------------------
 * 
 * 
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twfixedit;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.text.html.HTMLEditorKit;

/**
 * Zeigt eine HTML Datei in einen Frame an.
 * 
 * @author Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @version 1.0
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 */
public class TPSHtmlAnzeiger extends JFrame {

	private static final long serialVersionUID = 3257566204779574580L;

	private URL ivUrl;

	private File ivTempFile;

	private boolean ivDeleteOnExit;

	private static final int BREITE = 600;

	private static final int HOEHE = 550;

	/**
	 * Konstuktor zeigt die Install HTML Seite an.
	 * 
	 * @see java.lang.Object#Object()
	 */
	public TPSHtmlAnzeiger() {
		try {
			File file = new File("install.html");
			new TPSHtmlAnzeiger(file, "Info", false);
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

	/**
	 * Konstuktor.
	 * 
	 * @param pvFile
	 *            File Dateiname
	 * @param pvTitel
	 *            String Titel der oben in dem Frame angezeigt wird
	 * @param pvFlag
	 *            boolean wenn true wird Fenster beim Exit beendet
	 */
	@SuppressWarnings("deprecation")
	public TPSHtmlAnzeiger(File pvFile, String pvTitel, boolean pvFlag) {
		super(pvTitel);
		ivUrl = null;
		ivTempFile = null;
		ivDeleteOnExit = false;
		ivTempFile = pvFile;
		ivDeleteOnExit = pvFlag;
		JEditorPane lvEeditorPane = new JEditorPane();
		lvEeditorPane.setEditable(false);
		lvEeditorPane.setEditorKit(new HTMLEditorKit());
		setSize(BREITE, HOEHE);
		setVisible(true);
		try {
			lvEeditorPane.setPage(ivTempFile.toURL());
		} catch (Exception lvE) {
			String lvFehler = "<H1>Kann die HTML Datei " + pvFile.getAbsoluteFile() + " nicht finden.</H1>";
			System.out.println(lvFehler);
			lvEeditorPane.setText(lvFehler);
		}
		JScrollPane jscrollpane = new JScrollPane(lvEeditorPane);
		getContentPane().add(jscrollpane, "Center");
		setVisible(true);
	}

	/**
	 * Konstuktor.
	 * 
	 * @param pvTextInhalt
	 *            String der im Frame angezeigt wird
	 */
	public TPSHtmlAnzeiger(String pvTextInhalt,String pvTitel) {
		super(pvTitel);
		ivUrl = null;
		ivTempFile = null;
		ivDeleteOnExit = false;
		JEditorPane jeditorpane = new JEditorPane();
		jeditorpane.setEditable(false);
		jeditorpane.setEditorKit(new HTMLEditorKit());
		setSize(BREITE, HOEHE);
		jeditorpane.setText(pvTextInhalt);
		setVisible(true);
		JScrollPane jscrollpane = new JScrollPane(jeditorpane);
		getContentPane().add(jscrollpane, "Center");
		setVisible(true);
	}

	/**
	 * Konstuktor zeigt die URL im Fenster an.
	 * 
	 * @param pvUrl
	 *            URL die angezeigt wird
	 */
	public TPSHtmlAnzeiger(URL pvUrl) {
		ivTempFile = null;
		ivDeleteOnExit = false;
		ivUrl = pvUrl;
		JEditorPane lvEditorPane = new JEditorPane();
		lvEditorPane.setEditable(false);
		lvEditorPane.setEditorKit(new HTMLEditorKit());
		setSize(BREITE, HOEHE);
		setTitle("Bitte warten, die Seite wird geladen...");
		setVisible(true);
		try {
			lvEditorPane.setPage(ivUrl);
		} catch (Exception lvE) {
			String lvFehler = "<H1>Kann die HTML Datei " + ivUrl + " nicht finden.</H1>";
			System.out.println(lvFehler);
			lvEditorPane.setText(lvFehler);
		}
		setTitle(ivUrl.toString());
		JScrollPane jscrollpane = new JScrollPane(lvEditorPane);
		getContentPane().add(jscrollpane, "Center");
		setVisible(true);
	}

	/**
	 * Beendet das Fenster wenn Status true.
	 * 
	 * @exception IOException
	 */
	public void finalize() throws IOException {
		if (ivDeleteOnExit) {
			ivTempFile.delete();
		}
	}

	/**
	 * Method main Testcode.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		URL lvURL;
		try {
			lvURL = new URL("http://www.wenzlaff.de/twfixedit.html");
			System.out.println("URL= " + lvURL);
			new TPSHtmlAnzeiger(lvURL);
		} catch (MalformedURLException e) {
			System.out.println("URL ex=" + e.getMessage());
		}
	}
}