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
package de.wenzlaff.twautorename.gui;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.wenzlaff.twautorename.util.AppProperties;

/**
 * Ein Panel für den Suchen und Ersetzen Dialog.
 */
public class SuchenErsetzenExifPanel extends TextFieldPanel {


	private static final long serialVersionUID = 5152528094116209960L;

	private static final String icCLASSNAME = "de.wenzlaff.twautorename.gui.SuchenErsetzenExifPanel";

	/** Das Textfeld mit dem Begriff der gesucht werden soll. */
	public static final String TEXTFIELD_SUCHEN = "TEXTFIELD_SUCHEN";

	/** Das Textfeld mit dem Begriff der ersetzt werden soll. */
	public static final String TEXTFIELD_ERSETZEN = "TEXTFIELD_ERSETZEN";

	public static final String KEY_EXIF_DATEI = "EXIF_DATEI_ERSTELLEN";

	/** Ein Gui Element, das Suchen Feld */
	private JTextField ivSuchenField;

	/** Ein Gui Element, das Ersetzen Feld */
	private JComboBox ivErsetzenField;
	
	/** Erzeuge auch Exif Datei */
	private JCheckBox ivExif;


	/**
	 * Konstruktor für den Suchen und Ersetzen Panle.
	 */
	public SuchenErsetzenExifPanel() {
		ivTextArea.setText(AppProperties.getInstance().getProperties(icCLASSNAME + HILFE_TEXT_TEXTAREA));
		ivTextArea.setRows(10);
		JPanel lvPanel = new JPanel(new GridLayout(5, 1, 0, 20));
		lvPanel.setBorder(BorderFactory.createTitledBorder(""));
		lvPanel.add(new JLabel("Suchen nach (reguläre Pattern):"));
		ivSuchenField = new JTextField();
		ivSuchenField.setColumns(28);
		lvPanel.add(ivSuchenField);
		lvPanel.add(new JLabel("Ersetzen durch Exif Infos:"));
		ivErsetzenField = new JComboBox();
		ivErsetzenField.setEditable(true);
		ivErsetzenField.addItem("Erstellt_am_[exif-306]_");
		lvPanel.add(ivErsetzenField);
		ivExif = new JCheckBox("Erzeuge Exif Datei");
		lvPanel.add(ivExif);
		
		add(lvPanel);
	}

	/**
	 * @see de.wenzlaff.twautorename.gui.OptionGuiable#update()
	 */
	public void update() {
		ivMap.put(TEXTFIELD_SUCHEN, ivSuchenField.getText());
		ivMap.put(TEXTFIELD_ERSETZEN, ivErsetzenField.getSelectedItem().toString());
		ivMap.put(KEY_EXIF_DATEI, ivExif.isSelected() ? JA : NEIN);
	}
}
