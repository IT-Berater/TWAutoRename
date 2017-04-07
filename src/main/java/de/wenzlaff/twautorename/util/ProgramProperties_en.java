package de.wenzlaff.twautorename.util;

import java.io.IOException;
import java.util.PropertyResourceBundle;

public class ProgramProperties_en extends PropertyResourceBundle {
	/**
	 * Konstruktor.
	 * 
	 * @throws java.io.IOException
	 */
	public ProgramProperties_en() throws IOException {
		super(ProgramProperties_en.class.getResourceAsStream("/de/wenzlaff/twautorename/util/twautorename_en.properties"));
	}
}
