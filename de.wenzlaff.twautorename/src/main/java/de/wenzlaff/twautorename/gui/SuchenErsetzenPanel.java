/**
 * @author Copyright 2004 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 12.04.2004
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.gui;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import de.wenzlaff.twautorename.util.AppProperties;

/**
 * Ein Panel für den Suchen und Ersetzen Dialog.
 */
public class SuchenErsetzenPanel extends TextFieldPanel {

	private static final long serialVersionUID = 3834589898395889975L;

	private static final String icCLASSNAME = "de.wenzlaff.twautorename.gui.SuchenErsetzenPanel";

	/** Das Textfeld mit dem Begriff der gesucht werden soll. */
	public static final String TEXTFIELD_SUCHEN = "TEXTFIELD_SUCHEN";

	/** Das Textfeld mit dem Begriff der ersetzt werden soll. */
	public static final String TEXTFIELD_ERSETZEN = "TEXTFIELD_ERSETZEN";

	/** Ein Gui Element, das Suchen Feld */
	private JTextField ivSuchenField;

	/** Ein Gui Element, das Ersetzen Feld */
	private JTextField ivErsetzenField;

	/**
	 * Konstruktor für den Suchen und Ersetzen Panle.
	 */
	public SuchenErsetzenPanel() {
		ivTextArea.setText(AppProperties.getInstance().getProperties(icCLASSNAME + HILFE_TEXT_TEXTAREA));
		JPanel lvPanel = new JPanel(new GridLayout(4, 1, 0, 20));
		lvPanel.setBorder(BorderFactory.createTitledBorder(""));
		lvPanel.add(new JLabel("Suchen nach (reguläre Pattern):"));
		ivSuchenField = new JTextField();
		ivSuchenField.setColumns(28);
		lvPanel.add(ivSuchenField);
		lvPanel.add(new JLabel("Ersetzen durch:"));
		ivErsetzenField = new JTextField();
		ivErsetzenField.setColumns(28);
		lvPanel.add(ivErsetzenField);
		add(lvPanel);
	}

	/**
	 * @see de.wenzlaff.twautorename.gui.OptionGuiable#update()
	 */
	public void update() {
		ivMap.put(TEXTFIELD_SUCHEN, ivSuchenField.getText());
		ivMap.put(TEXTFIELD_ERSETZEN, ivErsetzenField.getText());
	}
}
