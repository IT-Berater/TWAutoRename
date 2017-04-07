/**
 * @author Copyright 2004 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 25.03.2004 18:45:53
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.plugins;

import java.io.File;

import javax.swing.JOptionPane;

import de.wenzlaff.twautorename.gui.OptionGuiable;
import de.wenzlaff.twautorename.util.AppProperties;

/**
 * Die Basis aller Plugins.
 */
public abstract class Plugin implements Renameable {

	/** Der Dateinamen der geändert wird. */
	protected String ivDateiname;

	/** Das Zielverzeichnis. */
	protected File ivFile;

	/** Das Gui Element. */
	private OptionGuiable ivGuiElement = null;

	/** Das eine Text Feld */
	protected final static String icTEXTFIELD_1 = "TEXTFIELD_1";

	/**
	 * Konstruktor.
	 * 
	 */
	public Plugin() {
		try {
			Class lvErweiterungClass = getClass().getClassLoader().loadClass(getGuiClass());
			ivGuiElement = (OptionGuiable) lvErweiterungClass.newInstance();
			System.out.println("GUI Instanz erzeugt: " + getGuiClass());
		} catch (ClassNotFoundException e) {
			System.out.println("Klasse nicht gefunden. setNewOptionPanel" + e.getMessage());
		} catch (InstantiationException e) {
			System.out.println("Kann keine Instanz anlegen. setNewOptionPanel" + e.getMessage());
		} catch (IllegalAccessException e) {
			System.out.println("Illegaler Zugriff. setNewOptionPanel" + e.getMessage());
		}
	}

	/**
	 * Gibt das Gui Objekt zurück.
	 * 
	 * @return das Gui Object
	 */
	public OptionGuiable getGuiObject() {
		return ivGuiElement;
	}

	public String getGuiClass() {
		return AppProperties.getInstance().getProperties(this.getClass().getName());
	}

	public String getPluginBeschreibung() {
		return "Die Plugin Beschreibung";
	}

	public String getZielDateiname() {
		return ivDateiname;
	}

	public String getZielPfad() {
		String lvPfad = ivFile.getAbsolutePath();
		int lvLen = ivFile.getName().length();
		return lvPfad.substring(0, lvPfad.length() - lvLen);
	}

	public void setDateiname(File pvDatei) {
		ivFile = pvDatei;
	}

	/**
	 * Zeigt eine fehler Nachricht an.
	 * 
	 * @param pvNachricht
	 *            die Nachricht die in einem Message Dialog angezeigt wird
	 */
	protected void showMessage(String pvNachricht) {
		JOptionPane.showMessageDialog(null, pvNachricht);
	}

	/**
	 * Gibt den Klassennamen als Key zurück.
	 * 
	 * @return String mit dem ganzen Klassennamen als Key z.B.
	 *         de.wenzlaff.twautorename.plugins.RenameKlein
	 */
	public String getPluginKey() {
		return getClass().getName();
	}
}
