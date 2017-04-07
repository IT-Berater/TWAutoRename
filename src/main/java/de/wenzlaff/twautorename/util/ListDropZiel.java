/**
 * 
 * ----------------------------------------
 * 
 * @author Copyright 2004 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 02.03.2004 23:38:47 ----------------------------------------
 * 
 * 
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.util;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * Klasse für das Listfeld um die gewählten Dateien aufzunehmen.
 */
public class ListDropZiel implements DropTargetListener {
	/** Die Dateiliste. */
	private JList ivFileList;

	/** Das Model der Dateiliste. */
	private DefaultListModel ivListModel;

	/**
	 * Konstruktor.
	 * 
	 * @param pvFileList
	 *            die JList mit den Filenamen
	 */
	public ListDropZiel(JList pvFileList) {
		ivFileList = pvFileList;
		ivListModel = (DefaultListModel) ivFileList.getModel();
	}

	/**
	 * @see java.awt.dnd.DropTargetListener#dragEnter(java.awt.dnd.DropTargetDragEvent)
	 */
	public void dragEnter(DropTargetDragEvent event) {
		if (!isDragAcceptable(event)) {
			event.rejectDrag();
			return;
		}
	}

	/**
	 * @see java.awt.dnd.DropTargetListener#dragExit(java.awt.dnd.DropTargetEvent)
	 */
	public void dragExit(DropTargetEvent event) {
	}

	/**
	 * @see java.awt.dnd.DropTargetListener#dragOver(java.awt.dnd.DropTargetDragEvent)
	 */
	public void dragOver(DropTargetDragEvent pvEvent) {
	}

	/**
	 * @see java.awt.dnd.DropTargetListener#dropActionChanged(java.awt.dnd.DropTargetDragEvent)
	 */
	public void dropActionChanged(DropTargetDragEvent event) {
		if (!isDragAcceptable(event)) {
			event.rejectDrag();
			return;
		}
	}

	/**
	 * @param event
	 * @return boolean ob Drag
	 */
	private boolean isDragAcceptable(DropTargetDragEvent event) {
		return (event.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0;
	}

	/**
	 * @param event
	 * @return boolean
	 */
	private boolean isDropAcceptable(DropTargetDropEvent event) {
		return (event.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0;
	}

	/**
	 * Gibt das Model der ausgewählten Daten.
	 * 
	 * @return DefaultListModel mit File Objekten
	 */
	public DefaultListModel getListModel() {
		return ivListModel;
	}

	/**
	 * Löscht die gelisteten Dateien.
	 */
	public void clearListModel() {
		ivListModel.clear();
	}

	/**
	 * @see java.awt.dnd.DropTargetListener#drop(java.awt.dnd.DropTargetDropEvent)
	 */
	public void drop(DropTargetDropEvent pvEvent) {
		if (!isDropAcceptable(pvEvent)) {
			pvEvent.rejectDrop();
			return;
		}
		// diese Anweisung behandelt alles als eine copy
		pvEvent.acceptDrop(DnDConstants.ACTION_COPY);
		Transferable lvTransver = pvEvent.getTransferable();
		DataFlavor[] lvFlavors = lvTransver.getTransferDataFlavors();
		for (int i = 0; i < lvFlavors.length; i++) {
			DataFlavor lvData = lvFlavors[i];
			if (lvData.equals(DataFlavor.javaFileListFlavor)) {
				try {
					List lvFileList = (List) lvTransver.getTransferData(lvData);
					Iterator lvIt = lvFileList.iterator();
					while (lvIt.hasNext()) {
						File lvFile = (File) lvIt.next();
						// System.out.println("Dateiname: " +
						// lvFile.getAbsolutePath());
						ivListModel.addElement(lvFile);
					}
				} catch (UnsupportedFlavorException e) {
					System.out.println("UnsupportedFlavorException" + e.getMessage());
				} catch (IOException e) {
					System.out.println("IOException" + e.getMessage());
				}
			}
		}
		pvEvent.dropComplete(true);
	}
	/**
	 * Ist ein Eintrag selektiert?
	 * @return boolean true wenn kein Eintrag selektiert ist sonst false
	 */
	public boolean isSelectionEmpty() {	
		return ivFileList.isSelectionEmpty();
	}
	/**
	 * Gibt den selektierten File Eintrag zurück.
	 * @return File das selektiert File.
	 */
	public File getSelectedValue() {
		return (File) ivFileList.getSelectedValue();
	}
}
