/**
 * ----------------------------------------
 * 
 * @author Copyright 2003 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 06.03.2004 13:12:16 ----------------------------------------
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.gui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import de.wenzlaff.twautorename.util.*;

/**
 * Fügt ein Postfix hinzu.
 */
public class TextPostfixPanel extends TextFieldPanel {

	private static final long serialVersionUID = 3257284734164744247L;

	private static final String icCLASSNAME = "de.wenzlaff.twautorename.gui.TextPostfixPanel";

	/** Ein Gui Element, das Textfeld die ergänzung des Dateinamens */
	private JTextField ivField;

	/**
	 * Konstruktor.
	 */
	public TextPostfixPanel() {
		ivTextArea.setText(AppProperties.getInstance().getProperties(icCLASSNAME + TextFieldPanel.HILFE_TEXT_TEXTAREA));
		JPanel lvPanel = new JPanel();
		lvPanel.setBorder(BorderFactory.createTitledBorder("Postfix:"));
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
