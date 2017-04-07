/**
 * @author Copyright 2007 by Thomas Wenzlaff ( <a
 *         href="mailto://gesundheitskarte@wenzlaff.de">gesundheitskarte@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 01.09.2007
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename;

import com.drew.metadata.Tag;

/**
 * Ein Key setzt sich zusammen aus: Den DirectoryName ein - und die TagType Nr.
 * 
 * z.B. Exif-306
 * 
 * @author Thomas Wenzlaff
 * 
 */
public class ExifKey {
	
	public enum ART{
		jpeg,
		jpegcomment,
		iptc,
		exif,
		interoperability
	}

	/**
	 * Gibt den Key zurück.
	 * 
	 * @return String mit dem Key.
	 */
	public static String getKey(final Tag pvTag) {
		return  "[" + pvTag.getDirectoryName().toLowerCase() + "-" + pvTag.getTagType() +"]";
	}
	public static String getKey(ART pvArt, int pvTagTypNr) {
		return  "[" + pvArt.name() + "-" + pvTagTypNr +"]";
	}

}
