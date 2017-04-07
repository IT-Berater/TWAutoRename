/**
 * @author Copyright 2007 by Thomas Wenzlaff ( <a
 *         href="mailto://gesundheitskarte@wenzlaff.de">gesundheitskarte@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 13.08.2007
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.allgemein.util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;

/**
 * <p>
 * This class provides a more flexible access to the clipboard. If a clipboard
 * is not present the methods do not terminate with an error.
 * 
 * <p>
 * Quicksilver 1.2 (an agent based simulation environment) <br>
 * LGPL 1997-2003 Jan Burse, XLOG Zrich
 */
public class Zwischenablage {

	/**
	 * Check whether access to the clipboard is granted.
	 * 
	 * @return true if access is granted, false otherwise.
	 */
	private static boolean hasClipboard() {
		SecurityManager sm = System.getSecurityManager();
		try {
			if (sm != null) {
				sm.checkSystemClipboardAccess();
			}
			return true;
		} catch (SecurityException x) {
			return false;
		}
	}

	/**
	 * Set the clipboard to a small unformatted representation. If no clipboard
	 * is available nothing happens.
	 * 
	 * @param s
	 *            The small unformatted representation.
	 */
	public static void setString(String s) {
		if (hasClipboard()) {
			StringSelection ss = new StringSelection(s);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(ss, ss);
		}
	}

	// /**
	// * Set the clipboard to a typed file.
	// *
	// * @param flavor The type.
	// * @param f The file.
	// */
	// public static void setResource(DataFlavor flavor, File f) {
	// if (hasClipboard()) {
	// FileTransferable rs = new FileTransferable(flavor, f.toString());
	// Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	// clipboard.setContents(rs, rs);
	// } else {
	// f.delete();
	// }
	// }

	/**
	 * Check whether the clipboad supports a given type.
	 * 
	 * @param flavor
	 *            The type.
	 * @return true If clipboard supports the type.
	 */
	public static boolean hasFlavor(DataFlavor flavor) {
		if (hasClipboard()) {
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable transfer = clipboard.getContents(null);
			if (transfer != null) {
				return transfer.isDataFlavorSupported(flavor);
			}
		}
		return false;
	}

	/**
	 * Retrieve the flavors of the scrapboard.
	 * 
	 * @return The flavors.
	 */
	public static DataFlavor[] getFlavors() {
		if (hasClipboard()) {
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable transfer = clipboard.getContents(null);
			if (transfer != null) {
				return transfer.getTransferDataFlavors();
			}
		}
		return new DataFlavor[] {};
	}

	/**
	 * Retrieve a string.
	 * 
	 * @return The small unformatted representation.
	 * @throws IOException
	 * @throws UnsupportedFlavorException
	 */
	public static String getString() throws IOException, UnsupportedFlavorException {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transfer = clipboard.getContents(null);
		return (String) transfer.getTransferData(DataFlavor.stringFlavor);
	}

	/**
	 * Retrieve a typed file.
	 * 
	 * @param flavor
	 *            The type.
	 * @return The large formatted representation.
	 * @throws IOException
	 * @throws UnsupportedFlavorException
	 */
	public static File getFile(DataFlavor flavor) throws IOException, UnsupportedFlavorException {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transfer = clipboard.getContents(null);
		return new File((String) transfer.getTransferData(flavor));
	}

	/**
	 * Create a temporary file.
	 * 
	 * @return The file.
	 * @throws IOException
	 */
	public static File newFile() throws IOException {
		return File.createTempFile("tmp", ".rsc");
	}

}
