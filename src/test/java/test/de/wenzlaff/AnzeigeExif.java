/**
 * @author Copyright 2007 by Thomas Wenzlaff ( <a
 *         href="mailto://gesundheitskarte@wenzlaff.de">gesundheitskarte@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 30.08.2007
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package test.de.wenzlaff;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import de.wenzlaff.twautorename.ExifAdapter;
import de.wenzlaff.twautorename.ExifKey;

/**
 * @author Thomas Wenzlaff
 * 
 */
public class AnzeigeExif {

	public static void main(String[] args) throws Exception {

		File jpegFile = new File("test.jpg");
		Metadata metadata = ImageMetadataReader.readMetadata(new BufferedInputStream(new FileInputStream(jpegFile)));

		Iterable<Directory> directories = metadata.getDirectories();
		for (Directory directory : directories) {

			Collection<Tag> tags = directory.getTags();
			for (Tag tag : tags) {
				System.out.println(tag.getDirectoryName() + " : " + tag);
			}
		}

		System.out.println("--------------:" + ExifAdapter.getMetaDataMap(jpegFile));
		System.out.println("Hole Datum:" + ExifAdapter.getMetaDataMap(jpegFile).get("[exif-306]"));
		System.out.println("Hole Datum:" + ExifAdapter.getMetaDataMap(jpegFile).get(ExifKey.getKey(ExifKey.ART.exif, 306)));
	}
}