/**
 * @author Copyright 2007 by Thomas Wenzlaff ( <a
 *         href="mailto://gesundheitskarte@wenzlaff.de">gesundheitskarte@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 17.05.2007
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.allgemein.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;

/**
 * Einige Gui Hilfsklassen.
 * 
 * @author Thomas Wenzlaff
 * @since 12.08.2007
 * @version 1.0
 */
public class GuiHelper {


	private static Dimension ivDimension = null; // singleton

	/**
	 * Look and Feel Konstanten.
	 */
	public final static String LAF_METAL = "javax.swing.plaf.metal.MetalLookAndFeel",
			LAF_MOTIF = "com.sun.java.swing.plaf.motif.MotifLookAndFeel",
			LAF_WINDOWS = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel",
			LAF_MAC = "com.sun.java.swing.plaf.mac.MacLookAndFeel", // Mac OS
			LAF_AQUA = "apple.laf.AquaLookAndFeel", // Mac OS X
			LAF_GTK = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel"; // GTK (with// J2SE	// 1.4.2)

	/**
	 * Da nur static.
	 */
	private GuiHelper() {
	}

	/**
	 * 
	 * @param pvComponent
	 */
	public static void centerComponent(Component pvComponent) {
		if (ivDimension == null)
			ivDimension = Toolkit.getDefaultToolkit().getScreenSize();
		pvComponent.setLocation(new java.awt.Point((ivDimension.width - pvComponent.getSize().width) / 2,
				(ivDimension.height - pvComponent.getSize().height) / 2));
	}

	/**
	 * Maximiert die Componente.
	 * 
	 * @param pvComponent
	 */
	public static void maximizeComponent(Component pvComponent) {
		if (ivDimension == null)
			ivDimension = Toolkit.getDefaultToolkit().getScreenSize();
		pvComponent.setSize(ivDimension);
		pvComponent.setLocation(0, 0);
	}

	/**
	 * Setzt das Look and Feel.
	 * 
	 */
	public static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception pvException) {

		}
	}

	/**
	 * Testet ob das Look and Feel verfügbar ist.
	 * 
	 * @param pvLaf
	 * @return boolean <code>true</code> wenn Look and Feel vorhanden.
	 */
	public static boolean isLookAndFeelAvailable(String pvLaf) {
		try {
			Class myclass = Class.forName(pvLaf);
			LookAndFeel lookandfeel = (LookAndFeel) myclass.newInstance();
			return lookandfeel.isSupportedLookAndFeel();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Sets the location of the window relative to the specified component. If
	 * the component is not currently showing, or <code>c</code> is
	 * <code>null</code>, the window is centered on the screen. If the bottom
	 * of the component is offscreen, the window is placed to the side of the
	 * <code>Component</code> that is closest to the center of the screen. So
	 * if the <code>Component</code> is on the right part of the screen, the
	 * <code>Window</code> is placed to its left, and visa versa.
	 * 
	 * @param c
	 *            the component in relation to which the window's location is
	 *            determined in awt.Window since 1.4 for compatibility with 1.3
	 */
	public static void setLocationRelativeTo(Component component, Component c) {
		Container root = null;

		if (c != null) {
			if (c instanceof Window) {
				root = (Container) c;
			} else {
				Container parent;
				for (parent = c.getParent(); parent != null; parent = parent.getParent()) {
					if (parent instanceof Window) {
						root = parent;
						break;
					}
				}
			}
		}

		if ((c != null && !c.isShowing()) || root == null || !root.isShowing()) {
			Dimension paneSize = component.getSize();
			Dimension screenSize = component.getToolkit().getScreenSize();

			component.setLocation((screenSize.width - paneSize.width) / 2, (screenSize.height - paneSize.height) / 2);
		} else {
			Dimension invokerSize = c.getSize();
			Point invokerScreenLocation;

			invokerScreenLocation = new Point(0, 0);
			Component tc = c;
			while (tc != null) {
				Point tcl = tc.getLocation();
				invokerScreenLocation.x += tcl.x;
				invokerScreenLocation.y += tcl.y;
				if (tc == root) {
					break;
				}
				tc = tc.getParent();
			}

			Rectangle windowBounds = component.getBounds();
			int dx = invokerScreenLocation.x + ((invokerSize.width - windowBounds.width) >> 1);
			int dy = invokerScreenLocation.y + ((invokerSize.height - windowBounds.height) >> 1);
			Dimension ss = component.getToolkit().getScreenSize();

			if (dy + windowBounds.height > ss.height) {
				dy = ss.height - windowBounds.height;
				dx = invokerScreenLocation.x < (ss.width >> 1) ? invokerScreenLocation.x + invokerSize.width
						: invokerScreenLocation.x - windowBounds.width;
			}
			if (dx + windowBounds.width > ss.width)
				dx = ss.width - windowBounds.width;
			if (dx < 0)
				dx = 0;
			if (dy < 0)
				dy = 0;
			component.setLocation(dx, dy);
		}
	}
}
