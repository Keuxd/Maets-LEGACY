package maets.download;

import java.io.IOException;
import java.net.URL;

public class Drive extends CloudStorageDownloader {
	
	private Drive() {}

	public static void downloadFile(String fileId, String localFilePath) throws IOException {
		downloadFromUrl("https://drive.google.com/uc?export=download&id=" + fileId + "&confirm=t", localFilePath);
	}

	public static boolean isFileAvailable(String fileId) throws IOException {
    	URL url = new URL("https://drive.google.com/uc?export=download&id=" + fileId + "&confirm=t");
    	long fileSize = url.openConnection().getContentLengthLong();
    	
    	if(fileSize == -1) return false;
    	else return true;
	}
}