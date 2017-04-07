/**
 * @author Copyright 2007 by Thomas Wenzlaff ( <a
 *         href="mailto://gesundheitskarte@wenzlaff.de">gesundheitskarte@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 30.08.2007
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package de.wenzlaff.twautorename;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JList;
import javax.swing.JOptionPane;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import de.wenzlaff.twfixedit.TPSHtmlAnzeiger;

/**
 * @author Thomas Wenzlaff
 * 
 */
public class ExifAdapter extends MouseAdapter {

	@Override
	public void mouseClicked(MouseEvent pvE) {
		if (pvE.getButton() == MouseEvent.BUTTON3) {
			// OK Rechte Maustaste gedrückt
			JList lvList = (JList) pvE.getSource();
			String lvFensterTitel = "";
			if (lvList != null) {
				File lvBildDatei = (File) lvList.getSelectedValue();

				if (lvBildDatei != null) {
					try {
						lvFensterTitel = lvBildDatei.getAbsolutePath();
						String lvExif = getMetaData(lvBildDatei);
						new TPSHtmlAnzeiger(lvExif, lvFensterTitel);
					} catch (ImageProcessingException pvException) {
						JOptionPane.showMessageDialog(null, "Die Datei " + lvBildDatei.getAbsolutePath() + " hat nicht das richtige Bildformat.");
					} catch (FileNotFoundException pvException) {
						JOptionPane.showMessageDialog(null, "Kann die Datei " + lvBildDatei.getAbsolutePath() + " nicht finden.");
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Kann die Datei " + lvBildDatei.getAbsolutePath() + " nicht lesen.");
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Wählen Sie mind. einen Eintrag aus der Liste aus.");
			}
		}
	}

	/**
	 * Gibt alle Metadaten der Bilddatei zurück.
	 * 
	 * @param pvBildDatei
	 * @return String mit allen Metadaten der Bilddatei.
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ImageProcessingException
	 * 
	 * @throws Exception
	 */
	public static String getMetaData(final File pvBildDatei) throws ImageProcessingException, FileNotFoundException, IOException {
		StringBuffer lvBuffer = new StringBuffer();
		File lvJpegFile = new File(pvBildDatei.getAbsolutePath());
		Metadata lvMetadata = ImageMetadataReader.readMetadata(new BufferedInputStream(new FileInputStream(lvJpegFile)));

		int lvAnzahlEinträge = 0;

		Iterable<Directory> lvDirectories = lvMetadata.getDirectories();

		for (Directory directory : lvDirectories) {

			Collection<Tag> tags = directory.getTags();
			for (Tag lvTag : tags) {

				String lvInhalt = lvTag.getTagName() + " - " + lvTag.getDescription();
				lvBuffer.append(ExifKey.getKey(lvTag) + " " + lvInhalt + "<br>");
				lvAnzahlEinträge++;

			}
		}
		lvBuffer.append("[twsoft] Anzahl der Einträge - " + lvAnzahlEinträge + "<br>");
		return lvBuffer.toString();
	}

	/**
	 * Gibt alle Metadaten der Bilddatei zurück.
	 * 
	 * @param pvBildDatei
	 * @return Map mit allen Metadaten der Bilddatei. Key einer der untenstehenden Werte wenn vorhanden.
	 * 
	 *         <pre>
	 *  
	 *  	 Important/interesting IPTC tags/placeholders, according to IPTC specification: 
	 *  
	 *  	 Placeholder Tag name/function  
	 *  	  
	 *  
	 *  	 $I5 -   Object Name  
	 *  	 
	 *  	 $I7 -   Edit Status  
	 *  	 
	 *  	 $I10 -   Priority  
	 *  	 
	 *  	 $I15 -   Category  
	 *  	 
	 *  	 $I20 -   Supplemental Category  
	 *  	 
	 *  	 $I25 -   Keywords  
	 *  	 
	 *  	 $I30 -   Release Date  
	 *  	 
	 *  	 $I35 -   Release Time  
	 *  	 
	 *  	 $I40 -   Special Instructions  
	 *  	 
	 *  	 $I45 -   Reference Service  
	 *  	 
	 *  	 $I47 -   Reference Date  
	 *  	 
	 *  	 $I50 -   Reference Number  
	 *  	 
	 *  	 $I55 -   Created Date  
	 *  	 
	 *  	 $I60 -   Created Time  
	 *  	 
	 *  	 $I65 -   Originating Program  
	 *  	 
	 *  	 $I70 -   Program Version  
	 *  	 
	 *  	 $I75 -   Object Cycle  
	 *  	 
	 *  	 $I80 -   Byline (Author)  
	 *  	 
	 *  	 $I85 -   Byline Title  
	 *  	 
	 *  	 $I90 -   City  
	 *  	 
	 *  	 $I95 -   Province State  
	 *  	 
	 *  	 $I100 -   Country Code  
	 *  	 
	 *  	 $I101 -   Country  
	 *  	 
	 *  	 $I103 -   Original Transmission Reference  
	 *  	 
	 *  	 $I105 -   Headline  
	 *  	 
	 *  	 $I110 -   Credit  
	 *  	 
	 *  	 $I115 -   Source  
	 *  	 
	 *  	 $I116 -   Copyright  
	 *  	 
	 *  	 $I120 -   Caption  
	 *  	 
	 *  	 $I121 -   Local Caption  
	 *  	 
	 *  	 $I122 -   Caption Writer  
	 *  	 
	 *  
	 *  	 Important/interesting EXIF tags/placeholders, according to EXIF specification: 
	 *  
	 *  	 Placeholder Tag name/function  
	 *  	 
	 *  
	 *  	 $E270 -    ImageDescription  
	 *  	 
	 *  	 $E271 -    Make  
	 *  	 
	 *  	 $E272 -    Model  
	 *  	 
	 *  	 $E274 -    Orientation  
	 *  	 
	 *  	 $E282 -    XResolution  
	 *  	 
	 *  	 $E283 -    YResolution  
	 *  	 
	 *  	 $E296 -    ResolutionUnit  
	 *  	 
	 *  	 $E305 -    Software  
	 *  	 
	 *  	 $E306 -    DateTime (see Appendix A for additional examples)  
	 *  	 
	 *  	 $E318 -   WhitePoint  
	 *  	 
	 *  	 $E531 -    YCbCrPositioning  
	 *  	 
	 *  	 $E532 -   ReferenceBlackWhite  
	 *  	 
	 *  	 $E33434 -   ExposureTime  
	 *  	 
	 *  	 $E33437 -   FNumber  
	 *  	 
	 *  	 $E34850 -   ExposureProgram  
	 *  	 
	 *  	 $E34855 -   ISOSpeedRatings  
	 *  	 
	 *  	 $E36864 -   ExifVersion  
	 *  	 
	 *  	 $E36867 -   DateTimeOriginal (see Appendix A for additional examples)  
	 *  	 
	 *  	 $E36868 -   DateTimeDigitized (see Appendix A for additional examples)  
	 *  	 
	 *  	 $E37121 -   ComponentsConfiguration  
	 *  	 
	 *  	 $E37122 -   CompressedBitsPerPixel  
	 *  	 
	 *  	 $E37377 -   ShutterSpeedValue  
	 *  	 
	 *  	 $E37378 -   ApertureValue  
	 *  	 
	 *  	 $E37379 -   BrightnessValue  
	 *  	 
	 *  	 $E37380 -   ExposureBiasValue  
	 *  	 
	 *  	 $E37381 -   MaxApertureValue  
	 *  	 
	 *  	 $E37382 -   SubjectDistance  
	 *  	 
	 *  	 $E37383 -   MeteringMode  
	 *  	 
	 *  	 $E37384 -   LightSource  
	 *  	 
	 *  	 $E37385 -   Flash  
	 *  	 
	 *  	 $E37386 -   FocalLength  
	 *  	 
	 *  	 $E37510 -   UserComment  
	 *  	 
	 *  	 $E40960 -   FlashPixVersion  
	 *  	 
	 *  	 $E40961 -   ColorSpace  
	 *  	 
	 *  	 $E40962 -   ExifImageWidth  
	 *  	 
	 *  	 $E40963 -   ExifImageHeight  
	 *  	 
	 *  	 $E41483 -   FlashEnergy  
	 *  	 
	 *  	 $E41486 -   FocalPlaneXResolution  
	 *  	 
	 *  	 $E41487 -   FocalPlaneYResolution  
	 *  	 
	 *  	 $E41488 -   FocalPlaneResolutionUnit  
	 *  	 
	 *  	 $E41492 -   SubjectLocation  
	 *  	 
	 *  	 $E41493 -   ExposureIndex  
	 *  	 
	 *  	 $E41495 -   SensingMethod  
	 *  	 
	 *  	 $E41728 -   FileSource  
	 *  	 
	 *  	 $E41729 -   SceneType  
	 *  	 
	 *  	 $E41985 -   CustomRendered  
	 *  	 
	 *  	 $E41986 -   ExposureMode  
	 *  	 
	 *  	 $E41987 -   WhiteBalance  
	 *  	 
	 *  	 $E41988 -   DigitalZoomRatio  
	 *  	 
	 *  	 $E41989 -   FocalLengthIn35mmFilm  
	 *  	 
	 *  	 $E41990 -   SceneCaptureType  
	 *  	 
	 *  	 $E41991 -   GainControl  
	 *  	 
	 *  	 $E41992 -   Contrast  
	 *  	 
	 *  	 $E41993 -   Saturation  
	 *  	 
	 *  	 $E41994 -   Sharpness  
	 *  	 
	 *  	 $E41995 -   DeviceSettingDescription  
	 *  	 
	 *  	 $E41996 -   SubjectDistanceRange  
	 *  	 
	 *  	 $E1 -   GPSLatitudeRef  
	 *  	 
	 *  	 $E2 -   GPSLatitude  
	 *  	 
	 *  	 $E3 -   GPSLongitudeRef  
	 *  	 
	 *  	 $E4 -   GPSLongitude  
	 *  	 
	 *  	 $E5 -   GPSAltitudeRef  
	 *  	 
	 *  	 $E6 -   GPSAltitude  
	 *  	 
	 *  	 $E7 -   GPSTimeStamp  
	 *  	 
	 *  
	 *  	 Note: You can also use special EXIF tags from vendor specifications (Nikon, Canon, Fuji, Casio, Olympus).  
	 *  	 
	 *  
	 *  	 Here is the list of tags for Nikon cameras (many models): 
	 *  
	 *  	 Placeholder Tag name/function  
	 *  	 	 
	 *  
	 *  	 $E1 -   Data Version  
	 *  	 
	 *  	 $E2 -   ISO Setting  
	 *  	 
	 *  	 $E3 -   Color Mode  
	 *  	 
	 *  	 $E4 -   Image Quality  
	 *  	 
	 *  	 $E5 -   White Balance  
	 *  	 
	 *  	 $E6 -   Image Sharpening  
	 *  	 
	 *  	 $E7 -   Focus Mode  
	 *  	 
	 *  	 $E8 -   Flash Setting  
	 *  	 
	 *  	 $E9 -   Flash Mode  
	 *  	 
	 *  	 $E11 -   White Balance Adjustment  
	 *  	 
	 *  	 $E14 -   Exposure Adjustment  
	 *  	 
	 *  	 $E15 -   ISO Selection  
	 *  	 
	 *  	 $E18 -   Flash Compensation  
	 *  	 
	 *  	 $E19 -   ISO 2  
	 *  	 
	 *  	 $E128 -   Image Adjustment  
	 *  	 
	 *  	 $E129 -   Tone Compensation  
	 *  	 
	 *  	 $E130 -   Auxiliary Lens  
	 *  	 
	 *  	 $E131 -   Lens Type  
	 *  	 
	 *  	 $E132 -   Lens  
	 *  	 
	 *  	 $E133 -   Manual Focus Distance  
	 *  	 
	 *  	 $E134 -   Digital Zoom  
	 *  	 
	 *  	 $E135 -   Flash Used  
	 *  	 
	 *  	 $E136 -   AF Focus Position  
	 *  	 
	 *  	 $E137 -   Bracketing  
	 *  	 
	 *  	 $E141 -   Color Mode  
	 *  	 
	 *  	 $E144 -   Light Type  
	 *  	 
	 *  	 $E146 -   Hue Adjustment  
	 *  	 
	 *  	 $E148 -   Saturation Adjustment  
	 *  	 
	 *  	 $E149 -   Noise Reduction  
	 *  	 
	 *  
	 *  	 Here is the list of tags for Canon cameras (many models): 
	 *  
	 *  	 Placeholder Tag name/function  
	 *  	  	 
	 *  
	 *  	 $E1 -   Macro mode  
	 *  	 
	 *  	 $E2 -   Self timer  
	 *  	 
	 *  	 $E3 -   Quality  
	 *  	 
	 *  	 $E4 -   Flash mode  
	 *  	 
	 *  	 $E5 -   Sequence mode  
	 *  	 
	 *  	 $E7 -   Focus mode  
	 *  	 
	 *  	 $E10 -   Image size  
	 *  	 
	 *  	 $E11 -   Easy shooting mode  
	 *  	 
	 *  	 $E12 -   Digital zoom  
	 *  	 
	 *  	 $E13 -   Contrast  
	 *  	 
	 *  	 $E14 -   Saturation  
	 *  	 
	 *  	 $E15 -   Sharpness  
	 *  	 
	 *  	 $E16 -   ISO Value  
	 *  	 
	 *  	 $E17 -   Metering mode  
	 *  	 
	 *  	 $E18 -   Focus type  
	 *  	 
	 *  	 $E19 -   AF point selected  
	 *  	 
	 *  	 $E20 -   Exposure mode  
	 *  	 
	 *  	 $E25 -   Focal length  
	 *  	 
	 *  	 $E28 -   Flash activity  
	 *  	 
	 *  	 $E29 -   Flash details  
	 *  	 
	 *  	 $E32 -   Focus mode 2  
	 *  	 
	 *  	 $E40 -   White Balance  
	 *  	 
	 *  	 $E41 -   Sequence number  
	 *  	 
	 *  	 $E42 -   AF point used  
	 *  	 
	 *  	 $E43 -   Flash bias  
	 *  	 
	 *  	 $E44 -   Subject Distance  
	 *  	 
	 *  	 $E60 -   Image Type  
	 *  	 
	 *  	 $E70 -   Firmware Version  
	 *  	 
	 *  	 $E80 -   Image Number  
	 *  	 
	 *  	 $E90 -   Owner Name  
	 *  	 
	 *  	 $E120 -   Camera Serial Number  
	 *  	 
	 *  	 Appendix A 
	 *  
	 *  	 By default, all date/time placeholders deliver full text including date and time. If you want to get only specific values, you can use additional placeholder options (ANSI C compatible): 
	 *  
	 *  	 %Y -   year, 4 numbers  
	 *  	 
	 *  	 %y -   year, 2 numbers (00-99)  
	 *  	 
	 *  	 %m -   month  
	 *  	 
	 *  	 %d -   day  
	 *  	 
	 *  	 %H -   hour  
	 *  	 
	 *  	 %M -   minute  
	 *  	 
	 *  	 %S -   second  
	 *  	 
	 * 
	 *  	 Examples for file, EXIF or system date: Year: 2005, month: 11, day: 29 
	 *  
	 *  	 Placeholder       Result text  
	 *  	 
	 *  
	 *  	 $T(%d.%m.%Y) - 29.11.2005  
	 *  	 
	 *  	 
	 *  	 $T(%Y%m%d) - 20051129  
	 *  	 
	 *  	 $T(%d%m%y)    - 291105  
	 *  	 
	 *  	 $E36868(%Y_%m_%d) - 2005_11_29  
	 *  	 
	 *  	 $T(%d_%m_%Y) - 29_11_2005  
	 *  	 
	 *  	 
	 *  	 $T(day:%d, month:%m, year: %Y) - day: 29, month: 11, year: 2005
	 * 
	 *         </pre>
	 * 
	 * @throws IOException
	 * 
	 */
	public static Map<String, String> getMetaDataMap(final File pvBildDatei) throws IOException {
		Map<String, String> lvRet = new HashMap<String, String>();
		File lvJpegFile = new File(pvBildDatei.getAbsolutePath());
		try {
			Metadata lvMetadata = ImageMetadataReader.readMetadata(new BufferedInputStream(new FileInputStream(lvJpegFile)));

			Iterable<Directory> directories = lvMetadata.getDirectories();
			for (Directory directory : directories) {

				Collection<Tag> tags = directory.getTags();
				for (Tag tag : tags) {
					lvRet.put(ExifKey.getKey(tag), tag.getDescription());

				}
			}
		} catch (ImageProcessingException pvException) {
		} catch (FileNotFoundException pvException) {
		}
		return lvRet;
	}

}
