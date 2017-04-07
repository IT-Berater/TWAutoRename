/**
 * ----------------------------------------
 * 
 * @author Copyright 2003 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 06.03.2004 17:27:06 ----------------------------------------
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.gui;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import de.wenzlaff.twautorename.util.*;

/**
 * Nummeriert die Dateien.
 */
public class TextCountPanel extends TextFieldPanel {

	private static final long serialVersionUID = 3832901044308226100L;

	public static final String icCLASSNAME = "de.wenzlaff.twautorename.gui.TextCountPanel";

	public static final String KEY_NUR_NUMMERIERUNG = "NURNUMMERIERUNG";

	public static final String KEY_RESET = "RESET";

	/** Ein Gui Element, das Textfeld die ergänzung des Dateinamens */
	private JTextField ivField;

	/** Nummerierung bei jedem Lauf beibehalten */
	private JCheckBox ivCheck;

	/** Nummerierung ohne Dateiname */
	private JCheckBox ivNurNummerierung;

	/**
	 * Konstruktor.
	 * 
	 */
	public TextCountPanel() {
		ivTextArea.setText(AppProperties.getInstance().getProperties(icCLASSNAME + HILFE_TEXT_TEXTAREA));
		JPanel lvPanel = new JPanel(new GridLayout(3, 1, 0, 20));
		lvPanel.setBorder(BorderFactory.createTitledBorder("Count:"));
		ivField = new JTextField();
		ivField.setColumns(28);
		lvPanel.add(ivField);
		ivCheck = new JCheckBox("Fortlaufend Nummerierung bei jedem Lauf");
		lvPanel.add(ivCheck);
		ivNurNummerierung = new JCheckBox("Nummerierung ohne Dateiname");
		lvPanel.add(ivNurNummerierung);
		add(lvPanel);
	}

	/**
	 * @see de.wenzlaff.twautorename.gui.OptionGuiable#update()
	 */
	public void update() {
		ivMap.put(TextFieldPanel.TEXTFIELD_1, ivField.getText());
		// Update wird beim Klick auf Start aufgerufen,
		// und wirkt auf das gleiche objekt
		ivMap.put(KEY_RESET, ivCheck.isSelected() ? JA : NEIN);
		ivMap.put(KEY_NUR_NUMMERIERUNG, ivNurNummerierung.isSelected() ? JA : NEIN);
	}
}
