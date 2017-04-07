/**
 * ----------------------------------------
 * 
 * @author Copyright 2003 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 18.03.2004 19:36:10 ----------------------------------------
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.gui;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JPanel;
import de.wenzlaff.twautorename.util.AppProperties;
import de.wenzlaff.twautorename.util.TWLog;
import de.wenzlaff.twfixedit.TPSHtmlAnzeiger;

/**
 * Klasse zum Löschen und Anzeigen des Logfiles.
 */
public class LogPanel extends TextFieldPanel {

	private static final long serialVersionUID = 3617014156726121266L;

	private static final String icCLASSNAME = "de.wenzlaff.twautorename.gui.LogPanel";

	/** Log löschen */
	private JButton ivLogClear;

	/** Log anzeigen */
	private JButton ivLogAnzeige;

	/**
	 * Konstruktor.
	 */
	public LogPanel() {
		ivTextArea.setText(AppProperties.getInstance().getProperties(icCLASSNAME + HILFE_TEXT_TEXTAREA));
		JPanel lvPanel = new JPanel(new GridLayout(3, 1, 0, 20));
		ivLogAnzeige = new JButton("Anzeigen");
		ivLogAnzeige.setMnemonic('A');
		ivLogAnzeige.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				new TPSHtmlAnzeiger(new File(TWLog.getDefaultLogFile()), "Log Datei", true);
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lvPanel.add(ivLogAnzeige);
		ivLogClear = new JButton("Löschen");
		ivLogClear.setMnemonic('C');
		ivLogClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TWLog.write(TWLog.LOG_VERAENDERT, "Log Datei gelöscht");
				TWLog.deleteLogFile();
			}
		});
		lvPanel.add(ivLogClear);
		add(lvPanel);
	}
}
