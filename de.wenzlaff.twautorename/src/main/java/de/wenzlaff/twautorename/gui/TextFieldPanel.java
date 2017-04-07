/**
 * 
 * ----------------------------------------
 * @author Copyright 2004 by Thomas Wenzlaff (<a href="mailto://java@wenzlaff.de">java@wenzlaff.de</a>) 
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de</a>
 * @since 02.03.2004 23:38:26
 * ----------------------------------------
 * 
 * 
 * 
 * Source is only for non commercial and coaching usage.
 * Not Warranty to use it.
 */
package de.wenzlaff.twautorename.gui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.wenzlaff.twautorename.util.AppProperties;

/**
 * Text Field Panel Klasse.
 */
public class TextFieldPanel extends GuiPanel {

	private static final long serialVersionUID = 3257854259713620280L;

	private static final String icCLASSNAME = "de.wenzlaff.twautorename.gui.TextFieldPanel";

	protected static final String HILFE_TEXT_TEXTAREA = ".TEXT_1";

	protected static final String TEXTFIELD_1 = "TEXTFIELD_1";

	protected JTextArea ivTextArea;

	/**
	 * Konstruktor.
	 */
	public TextFieldPanel() {

		JPanel lvPanel = new JPanel();
		ivTextArea = new JTextArea(AppProperties.getInstance().getProperties(icCLASSNAME + HILFE_TEXT_TEXTAREA));
		ivTextArea.setColumns(28);
		ivTextArea.setRows(20);
		ivTextArea.setAutoscrolls(true);
		ivTextArea.setEditable(false);
		ivTextArea.setLineWrap(true);
		ivTextArea.setWrapStyleWord(true);
		lvPanel.add(ivTextArea);
		lvPanel.setBorder(BorderFactory.createTitledBorder("Info"));
		add(lvPanel);
	}
}
