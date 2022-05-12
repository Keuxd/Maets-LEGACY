package maets.core;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.github.junrar.Junrar;
import com.github.junrar.exception.RarException;

public class ExtractUtil {
	
	private ExtractUtil() {}

	public static void extract(String extractionFilePath, String dirPath) throws IOException, RarException {
		// If non directory is provided then extract the zip/rar in the same folder
		if(dirPath == null || dirPath.isEmpty()) {
			dirPath = new File(extractionFilePath).getParent();
		}
		
		if(extractionFilePath.endsWith(".rar")) {
			extractRar(extractionFilePath, dirPath);
		} else if(extractionFilePath.endsWith(".zip")) {
			extractZip(extractionFilePath, dirPath);
		} else {
			throw new IOException("Extraction file extension isn't valid.");
		}
	}
	
	private static void extractRar(String rar, String dir) throws RarException, IOException {
		Junrar.extract(rar, dir);
	}
	
	private static void extractZip(String zip, String dir) throws IOException {
		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zip));
		ZipEntry entry = zipIn.getNextEntry();
		
		while(entry != null) {
			String filePath = dir + File.separator + entry.getName();
			if(!entry.isDirectory()) {
				extractZipFile(zipIn, filePath);
			} else {
				new File(filePath).mkdirs();
			}
			zipIn.closeEntry();
			entry = zipIn.getNextEntry();
		}
		zipIn.close();
	}
	
	private static void extractZipFile(ZipInputStream zipIn, String filePath) throws IOException {
		// Default buffer size is 4096(4x) however 16384(16x) worked pretty well in tests
		final int BUFFER_SIZE = 1024 * 16;
		
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
		byte[] bytesIn = new byte[BUFFER_SIZE];
		
		int read = 0;
		while((read = zipIn.read(bytesIn)) != -1) {
			bos.write(bytesIn, 0, read);
		}
		bos.close();
	}
}