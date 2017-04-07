/**
 * ----------------------------------------
 * 
 * @author Copyright 2003 by Thomas Wenzlaff ( <a
 *         href="mailto://java@wenzlaff.de">java@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 14.03.2004 13:55:29 ----------------------------------------
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename.util;

import java.io.IOException;
import java.util.PropertyResourceBundle;

/**
 * Hilfsklklasse zum laden von Properties Dateien.
 */
public class ProgramProperties_de extends PropertyResourceBundle {
	/**
	 * Konstruktor.
	 * 
	 * @throws java.io.IOException
	 */
	public ProgramProperties_de() throws IOException {
		super(ProgramProperties_de.class.getResourceAsStream("/de/wenzlaff/twautorename/util/twautorename.properties"));
	}
}
