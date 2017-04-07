/**
 * @author Copyright 2004 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 25.03.2004 18:53:03
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.drew.imaging.ImageProcessingException;

import de.wenzlaff.allgemein.util.GuiHelper;
import de.wenzlaff.allgemein.util.Version;
import de.wenzlaff.twautorename.gui.TextFieldPanel;
import de.wenzlaff.twautorename.plugins.PluginView;
import de.wenzlaff.twautorename.plugins.Renameable;
import de.wenzlaff.twautorename.util.AppProperties;
import de.wenzlaff.twautorename.util.ListDropZiel;
import de.wenzlaff.twautorename.util.TWLog;
import de.wenzlaff.twautorename.util.WindowClosingAdapter;
import de.wenzlaff.twfixedit.TPSHtmlAnzeiger;

/**
 * Die Hauptklasse mit der Main zum starten.
 */
public class TWAutoRename extends JFrame {

	private static final long serialVersionUID = 3257564027281684787L;

	/** Das Listfeld auf der linken Seite mit allen Dateinamen */
	private ListDropZiel ivListDropZiel;

	private JToolBar ivToolbar;

	private JPanel ivPanel;

	private Container ivContentPane;

	private JComponent ivOptionPanel;

	private JComboBox ivComboBox;

	/**
	 * Enthï¿½lt alle Plugins als RenamFactory Objekte, der Key ist der Klassenname
	 */
	private Map<String, Renameable> ivAllPlugins;

	private JMenuBar jJMenuBar = null;

	private JMenu ivMenuFile = null;

	private JMenu ivMenuHilfe = null;

	private JMenuItem ivMenuItemEnde = null;

	private JMenuItem ivMenuItemHomepage = null;

	private JMenuItem ivMenuItemHife = null;

	private JMenu ivMenuBild = null;

	private JMenuItem ivMenuItemInformation = null;

	/**
	 * Konstruktor.
	 */
	public TWAutoRename() {
		TWLog.write(TWLog.LOG_INFORMATION, "Programm START");
		setTitle(Version.PROGRAMM_NAME);
		setSize(800, 650);
		iniPlugin();
		iniComponent();
		// das Anwendungs Icon
		setIconImage(Toolkit.getDefaultToolkit().createImage(TWAutoRename.class.getResource("/de/wenzlaff/twautorename/util/twautorename.gif")));
		ivMenuItemInformation.setEnabled(!ivListDropZiel.isSelectionEmpty());
	}

	/**
	 * Ini aller Plugins. Erzeuge alle Instanzen die in den Properties gelistet sind.
	 */
	private void iniPlugin() {
		Map lvProper = AppProperties.getInstance().getKlassen();
		ivAllPlugins = new HashMap<String, Renameable>();
		Iterator lvIter = lvProper.keySet().iterator();
		try {
			while (lvIter.hasNext()) {
				String lvKey = (String) lvIter.next();
				if (lvKey.indexOf("_") == -1) // keine Klassennamen mit _ aus
				// den prop lesen
				{
					try {
						Class lvClass = getClass().getClassLoader().loadClass(lvKey);
						Renameable ivPlugFactory = (Renameable) lvClass.newInstance();
						System.out.println("Plugin Instanz erzeugt: " + ivPlugFactory.getPluginKey());
						ivAllPlugins.put(lvKey, ivPlugFactory);
					} catch (ClassNotFoundException e) {
						System.out.println("ClassNotFoundException: Kann die Klasse " + lvKey + " nicht finden. Ex=" + e.getMessage());
					}
				}
			}
		} catch (InstantiationException e) {
			System.out.println("iniPlugin" + e.getMessage());
		} catch (IllegalAccessException e) {
			System.out.println("iniPlugin" + e.getMessage());
		}
	}

	private void iniComponent() {
		ivContentPane = getContentPane();
		setJMenuBar(getJJMenuBar());
		ivContentPane.add(getToolbar(), BorderLayout.NORTH);
		ivPanel = new JPanel(new GridLayout(1, 2));
		ivPanel.add(getFileListComponent());
		ivOptionPanel = getDefaultStartComponente();
		ivPanel.add(ivOptionPanel);
		ivContentPane.add(ivPanel);
	}

	/**
	 * Setzt den Optionspanel rechts neu.
	 * 
	 * @param pvView
	 *            fï¿½r den rechten Optionspanel
	 */
	public void setNewOptionPanel(PluginView pvView) {
		ivPanel.remove(ivOptionPanel);
		ivPanel.validate();
		ivOptionPanel = pvView.getFactory().getGuiObject().getOptionsPanel();
		ivPanel.add(ivOptionPanel);
		ivPanel.validate();
	}

	/**
	 * Die Toolbar.
	 * 
	 * @return Component die Toolbar
	 */
	private JComponent getToolbar() {
		ivToolbar = new JToolBar();

		JButton lvClear = new JButton("Löschen");
		lvClear.setMnemonic('l');
		lvClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent pvEvent) {
				ivListDropZiel.clearListModel();
			}
		});
		JButton lvStart = new JButton("Start");
		lvStart.setMnemonic('s');
		lvStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent pvEvent) {
				actionPerformedButtonRenameKlick();
			}
		});
		ivComboBox = new JComboBox();
		Iterator lvIter = ivAllPlugins.entrySet().iterator();
		while (lvIter.hasNext()) {
			Map.Entry lvEntry = (Map.Entry) lvIter.next();
			Renameable lvIns = (Renameable) lvEntry.getValue();
			ivComboBox.addItem(new PluginView(lvIns));
		}
		ivComboBox.setSelectedIndex(-1);
		ivComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent pvEvent) {
				System.out.println("Plugin geï¿½ndert: " + ((PluginView) ivComboBox.getSelectedItem()).getFactory().getPluginBeschreibung());
				setNewOptionPanel((PluginView) ivComboBox.getSelectedItem());
			}
		});
		ivToolbar.add(ivComboBox);
		ivToolbar.addSeparator();
		ivToolbar.addSeparator();
		ivToolbar.add(lvClear);
		ivToolbar.addSeparator();
		ivToolbar.add(lvStart);
		ivToolbar.addSeparator();
		return ivToolbar;
	}

	/**
	 * Ini des linken Listfeld wo die Dateien abgelegt werden:
	 * 
	 * @return JComponent
	 */
	private JComponent getFileListComponent() {
		JList lvFileListe = new JList(new DefaultListModel());
		lvFileListe.addMouseListener(new ExifAdapter());
		lvFileListe.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent pvE) {
				ivMenuItemInformation.setEnabled(!ivListDropZiel.isSelectionEmpty());
			}
		});
		lvFileListe.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		UIManager.put("List.background", new Color(255, 255, 230));
		lvFileListe.updateUI();
		JScrollPane lvScrollPane = new JScrollPane(lvFileListe);
		lvScrollPane.setBorder(BorderFactory.createEtchedBorder());
		lvScrollPane.setAutoscrolls(true);
		ivListDropZiel = new ListDropZiel(lvFileListe);

		new DropTarget(lvFileListe, ivListDropZiel);
		return lvScrollPane;
	}

	/**
	 * Die rechte Seite mit den Optionen.
	 * 
	 * @return JComponent
	 */
	private JComponent getDefaultStartComponente() {
		return new TextFieldPanel();
	}

	/**
	 * Button Start gedrï¿½ckt.
	 */
	private void actionPerformedButtonRenameKlick() {
		int lvCount = 0;
		int lvDirs = 0;
		// wenn nichts ausgewï¿½hlt ist, mach auch nichts
		if (ivComboBox.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Bitte erst eine Auswahl einer Funktion in der Combobox oben!");
			return;
		}
		// hole den selektierten Eintrag
		Renameable lvSelPlugin = ((PluginView) ivComboBox.getSelectedItem()).getFactory();
		// aktualisiere die Eingaben
		lvSelPlugin.getGuiObject().update();
		Enumeration lvEnum = ivListDropZiel.getListModel().elements();
		if (ivListDropZiel.getListModel().size() < 1) {
			JOptionPane.showMessageDialog(this,
					"Bitte erst mit der Maus und drag and dropt\n(Dateien auswï¿½hlen mit gedrï¿½ckter rechten Maustaste ï¿½ber den gelben Bereich fallen lassen)\nDateien oder Verzeichnisse in den linke gelbe Bereich des Programmes einfï¿½gen.");
			return;
		}
		TWLog.write(TWLog.LOG_INFORMATION, "Verwende Plugin: " + lvSelPlugin.getPluginBeschreibung());
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		while (lvEnum.hasMoreElements()) {
			File lvFileIn = (File) lvEnum.nextElement();
			if (lvFileIn.exists()) {
				if (lvFileIn.canWrite()) {
					lvSelPlugin.setDateiname(lvFileIn);
					if (lvFileIn.isDirectory()) {
						lvDirs++;
					}
					File lvFileOut;
					try {
						lvFileOut = new File(lvSelPlugin.getZielPfad(), lvSelPlugin.getZielDateiname());
					} catch (RuntimeException e) {
						TWLog.write(TWLog.LOG_FEHLER, "Fehler in den Parametern");
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						return;
					}
					if (lvFileIn.isDirectory()) {
						TWLog.write(TWLog.LOG_INFORMATION, "Verzeichnis:");
					}
					if (lvFileIn.isHidden()) {
						TWLog.write(TWLog.LOG_INFORMATION, "Versteckte:");
					}
					TWLog.write(TWLog.LOG_VERAENDERT, "Quell Datei:    " + lvFileIn.getAbsolutePath());
					TWLog.write(TWLog.LOG_VERAENDERT, "Ziel Datei : -->" + lvSelPlugin.getZielPfad() + lvSelPlugin.getZielDateiname());
					if (lvFileOut.exists()) {
						TWLog.write(TWLog.LOG_INFORMATION,
								"Datei nicht umbenannt, da eine gleiche Datei bereits existiert: " + lvSelPlugin.getZielPfad() + lvSelPlugin.getZielDateiname());
					} else {
						try {
							lvFileIn.renameTo(lvFileOut);
							lvCount++;
						} catch (SecurityException e1) {
							TWLog.write(TWLog.LOG_FEHLER, "Konnte Datei nicht zugreifen:  " + lvSelPlugin.getZielPfad() + lvSelPlugin.getZielDateiname());
						}
					}
				} else {
					TWLog.write(TWLog.LOG_FEHLER, "Datei ist schreibgeschï¿½tzt, nicht geï¿½ndert: " + lvFileIn.getName());
				}
			} else {
				TWLog.write(TWLog.LOG_FEHLER, "Datei ist nicht mehr vorhanden: " + lvFileIn.getName());
			}
		}
		ivListDropZiel.clearListModel();
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		if (lvDirs > 0) {
			TWLog.write(TWLog.LOG_INFORMATION, "Umbenennen von " + lvDirs + " Verzeichnissen fertig.");
		}
		TWLog.write(TWLog.LOG_INFORMATION, "Umbenennen von " + lvCount + " Dateien fertig.");
	}

	/**
	 * This method initializes jJMenuBar
	 * 
	 * @return javax.swing.JMenuBar
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getIvMenuFile());
			jJMenuBar.add(getIvMenuBild());
			jJMenuBar.add(getIvMenuHilfe());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes ivMenuFile
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getIvMenuFile() {
		if (ivMenuFile == null) {
			ivMenuFile = new JMenu();
			ivMenuFile.setText("Datei");
			ivMenuFile.setMnemonic(KeyEvent.VK_D);
			ivMenuFile.add(getIvMenuItemEnde());
		}
		return ivMenuFile;
	}

	/**
	 * This method initializes ivMenuHilfe
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getIvMenuHilfe() {
		if (ivMenuHilfe == null) {
			ivMenuHilfe = new JMenu();
			ivMenuHilfe.setText("Hilfe");
			ivMenuHilfe.setMnemonic(KeyEvent.VK_H);
			ivMenuHilfe.add(getIvMenuItemHife());
			ivMenuHilfe.add(getIvMenuItemHomepage());
		}
		return ivMenuHilfe;
	}

	/**
	 * This method initializes ivMenuItemEnde
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getIvMenuItemEnde() {
		if (ivMenuItemEnde == null) {
			ivMenuItemEnde = new JMenuItem();
			ivMenuItemEnde.setText("Beenden");
			ivMenuItemEnde.setMnemonic(KeyEvent.VK_B);
			ivMenuItemEnde.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return ivMenuItemEnde;
	}

	/**
	 * This method initializes ivMenuItemHomepage
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getIvMenuItemHomepage() {
		if (ivMenuItemHomepage == null) {
			ivMenuItemHomepage = new JMenuItem();
			ivMenuItemHomepage.setText("Homepage...");
			ivMenuItemHomepage.setMnemonic(KeyEvent.VK_O);
			ivMenuItemHomepage.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Desktop.getDesktop().browse(new URI("http://www.wenzlaff.de"));
					} catch (IOException pvException) {
					} catch (URISyntaxException pvException) {
					}
				}
			});
		}
		return ivMenuItemHomepage;
	}

	/**
	 * This method initializes ivMenuItemHife
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getIvMenuItemHife() {
		if (ivMenuItemHife == null) {
			ivMenuItemHife = new JMenuItem();
			ivMenuItemHife.setName("");
			ivMenuItemHife.setText("Anleitung...");
			ivMenuItemHife.setMnemonic(KeyEvent.VK_I);
			ivMenuItemHife.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Desktop.getDesktop().browse(new URI("http://www.wenzlaff.de/twautorename.html"));
					} catch (IOException pvException) {
					} catch (URISyntaxException pvException) {
					}
				}
			});
		}
		return ivMenuItemHife;
	}

	/**
	 * This method initializes ivMenuBild
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getIvMenuBild() {
		if (ivMenuBild == null) {
			ivMenuBild = new JMenu();
			ivMenuBild.setText("Bild");
			ivMenuBild.setMnemonic(KeyEvent.VK_B);
			ivMenuBild.add(getIvMenuItemInformation());
		}
		return ivMenuBild;
	}

	/**
	 * This method initializes ivMenuItemInformation
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getIvMenuItemInformation() {
		if (ivMenuItemInformation == null) {
			ivMenuItemInformation = new JMenuItem();
			ivMenuItemInformation.setText("Information");
			ivMenuItemInformation.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					File lvBildDatei = ivListDropZiel.getSelectedValue();
					String lvBildDateiNamen = lvBildDatei.getAbsolutePath();
					try {
						String lvExif = ExifAdapter.getMetaData(lvBildDatei);
						new TPSHtmlAnzeiger(lvExif, lvBildDateiNamen);
					} catch (ImageProcessingException pvException) {
						JOptionPane.showMessageDialog(null, "Das Format wird für die Datei " + lvBildDateiNamen + " nicht unterstützt.");
					} catch (FileNotFoundException pvException) {
						JOptionPane.showMessageDialog(null, "Kann die Datei " + lvBildDateiNamen + " nicht finden.");
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Kann die Datei " + lvBildDateiNamen + " nicht lesen.");
					}
				}
			});
		}
		return ivMenuItemInformation;
	}

	/**
	 * Diese Main startet das Programm.
	 * 
	 * @param args
	 *            keine Argumente
	 */
	public static void main(String[] args) {
		TWAutoRename lvMainGui = new TWAutoRename();
		lvMainGui.addWindowListener(new WindowClosingAdapter(true));
		GuiHelper.centerComponent(lvMainGui);
		lvMainGui.setVisible(true);
	}
}
