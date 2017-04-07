/**
 * 
 * ----------------------------------------
 * 
 * @author Copyright 2004 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 02.03.2004 23:38:17 ----------------------------------------
 * 
 * 
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.gui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import de.wenzlaff.twautorename.util.AppProperties;

/**
 * Ändert den Dateinamen mit Textergänzungen.
 */
public class TextPrefixPanel extends TextFieldPanel {

	private static final long serialVersionUID = 3907210468157174584L;

	private static final String icCLASSNAME = "de.wenzlaff.twautorename.gui.TextPrefixPanel";

	/** Ein Gui Element, das Textfeld die ergänzung des Dateinamens */
	private JTextField ivField;

	/**
	 * Konstruktor.
	 */
	public TextPrefixPanel() {
		ivTextArea.setText(AppProperties.getInstance().getProperties(icCLASSNAME + HILFE_TEXT_TEXTAREA));
		JPanel lvPanel = new JPanel();
		lvPanel.setBorder(BorderFactory.createTitledBorder("Prefix:"));
		ivField = new JTextField();
		ivField.setColumns(28);
		lvPanel.add(ivField);
		add(lvPanel);
	}

	/**
	 * @see de.wenzlaff.twautorename.gui.OptionGuiable#update()
	 */
	public void update() {
		ivMap.put(TextFieldPanel.TEXTFIELD_1, ivField.getText());
	}
}
