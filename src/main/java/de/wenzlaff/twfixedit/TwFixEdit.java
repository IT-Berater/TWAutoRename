/**
 * ----------------------------------------
 * 
 * @author Copyright 2004 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 12.01.2004 18:25:56 ----------------------------------------
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twfixedit;

import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultEditorKit;

/**
 * Klasse zum schnellen Editieren von Dateilisten und Pfaden per Drag and Drop.
 * TODO Eigenes Icon für die Anwendung TODO Vorschau Fenster wie die aussehen
 * würden TODO INNO Setup Script TODO Prüfen ob JAVAinstallier und evl. Fehler
 * anzeigen
 */
public class TwFixEdit extends JFrame {

	private static final long serialVersionUID = 3617294545109136180L;

	/** Der Anwendungs Fenster Titel. */
	private static final String FENSTER_TITEL = "TwFixEdit";

	/** Die Textarea. */
	private JTextArea ivTextArea;

	/** Das Popup Menü. */
	private JPopupMenu ivPopMenue;

	/** Der Popup Menü Item Löschen. */
	private JMenuItem ivItemLoeschen;

	/** Der Popup Menü Item Einfügen. */
	private JMenuItem ivItemPast;

	/** Der Popup Menü Item Kopieren. */
	private JMenuItem ivItemCopy;

	/**
	 * Konstruktor.
	 */
	public TwFixEdit() {
		setTitle(FENSTER_TITEL);
		setSize(800, 600);
		ivTextArea = new JTextArea();
		ivTextArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		JScrollPane ivPanel = new JScrollPane(ivTextArea);
		setContentPane(ivPanel);
		// das Drop Ziel festlegen für die Textarea
		new DropTarget(ivTextArea, new TextAreaDroptZiel(ivTextArea));
		// ein Popup Menue erstellen
		ivPopMenue = new JPopupMenu();
		ivItemLoeschen = new JMenuItem("Anzeige löschen");
		ivItemLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ivTextArea.setText("");
			}
		});
		ivPopMenue.add(ivItemLoeschen);
		ivPopMenue.addSeparator();
		// Einfügen
		ivItemPast = new JMenuItem("Einfügen");
		ivItemPast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent pvEvent) {
				new DefaultEditorKit.PasteAction().actionPerformed(pvEvent);
			}
		});
		ivPopMenue.add(ivItemPast);
		ivPopMenue.addSeparator();
		// Kopieren
		ivItemCopy = new JMenuItem("Kopieren");
		ivItemCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent pvEvent) {
				new DefaultEditorKit.CopyAction().actionPerformed(pvEvent);
			}
		});
		ivPopMenue.add(ivItemCopy);
		// das Popup Menü erscheinen lassen auf der Maus position
		ivTextArea.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent pvEvent) {
				if (pvEvent.isPopupTrigger()) {
					ivPopMenue.show(pvEvent.getComponent(), pvEvent.getX(), pvEvent.getY());
				}
			}
		});
	}

	/**
	 * Der Startpunkt der Anwendung.
	 * 
	 * @param args
	 *            keine Argumente
	 */
	public static void main(String[] args) {
		TwFixEdit lvAppFrame = new TwFixEdit();
		lvAppFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lvAppFrame.setVisible(true);
	}
}

/**
 * Die Klasse für das Dropt Ziel.
 */

class TextAreaDroptZiel implements DropTargetListener {
	/** Referenz auf die Text Area. */
	private JTextArea ivTextArea;

	/**
	 * Konstruktor.
	 * 
	 * @param pvTextArea
	 *            die Textarea
	 */
	public TextAreaDroptZiel(JTextArea pvTextArea) {
		ivTextArea = pvTextArea;
	}

	/**
	 * @see java.awt.dnd.DropTargetListener#dragEnter(java.awt.dnd.DropTargetDragEvent)
	 */
	public void dragEnter(DropTargetDragEvent pvEvent) {
		if (!isDragAcceptable(pvEvent)) {
			pvEvent.rejectDrag();
			return;
		}
	}

	/**
	 * @see java.awt.dnd.DropTargetListener#dropActionChanged(java.awt.dnd.DropTargetDragEvent)
	 */
	public void dropActionChanged(DropTargetDragEvent pvEvent) {
		if (!isDragAcceptable(pvEvent)) {
			pvEvent.rejectDrag();
			return;
		}
	}

	/**
	 * @see java.awt.dnd.DropTargetListener#dragExit(java.awt.dnd.DropTargetEvent)
	 */
	public void dragExit(DropTargetEvent pvEvent) {
	}

	/**
	 * @see java.awt.dnd.DropTargetListener#dragOver(java.awt.dnd.DropTargetDragEvent)
	 */
	public void dragOver(DropTargetDragEvent pvEvent) {
	}

	/**
	 * @see java.awt.dnd.DropTargetListener#drop(java.awt.dnd.DropTargetDropEvent)
	 */
	public void drop(DropTargetDropEvent pvEvent) {
		if (!isDropAcceptable(pvEvent)) {
			pvEvent.rejectDrop();
			return;
		}
		pvEvent.acceptDrop(DnDConstants.ACTION_COPY);
		Transferable lvTransver = pvEvent.getTransferable();
		DataFlavor[] lvFlavors = lvTransver.getTransferDataFlavors();
		try {
			for (int i = 0; i < lvFlavors.length; i++) {
				DataFlavor lvData = lvFlavors[i];
				if (lvData.equals(DataFlavor.javaFileListFlavor)) {
					List lvFileList = (List) lvTransver.getTransferData(lvData);
					Iterator lvIt = lvFileList.iterator();
					while (lvIt.hasNext()) {
						File lvFile = (File) lvIt.next();
						if (pvEvent.getDropAction() == DnDConstants.ACTION_COPY) {
							// wenn STRG gedrückt...
							ivTextArea.append(lvFile.getName() + "\n");
						} else {
							// bei normalen dropt...
							ivTextArea.append(lvFile.getAbsolutePath() + "\n");
						}
					}
				}
			}
		} catch (Exception e) {
			ivTextArea.setText(e.getMessage() + "\n");
		}
		pvEvent.dropComplete(true);
	}

	/**
	 * Ist Drag Acceptable.
	 * 
	 * @param pvEvent
	 *            der Event
	 * @return boolean wenn ja true
	 */
	private boolean isDragAcceptable(DropTargetDragEvent pvEvent) {
		return (pvEvent.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0;
	}

	/**
	 * Ist Dropt Acceptable.
	 * 
	 * @param pvEvent
	 *            der Event
	 * @return boolean wenn ja true
	 */
	private boolean isDropAcceptable(DropTargetDropEvent pvEvent) {
		return (pvEvent.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0;
	}
}
