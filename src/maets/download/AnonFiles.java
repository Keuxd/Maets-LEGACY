package maets.download;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AnonFiles extends CloudStorageDownloader {
	
	private AnonFiles() {}

	public static void downloadFile(String fileId, String localFilePath) throws IOException {
		downloadFromUrl(getAnonDownloadLink("https://anonfiles.com/" + fileId), localFilePath);
	}

	public static boolean isAnonFileAvailable(String fileId) throws IOException {
		try {
			int linkIndex = SocketConnection.getURLSource("https://anonfiles.com/" + fileId).indexOf("https://cdn-");
			
			if(linkIndex == -1) return false;
			else return true;
		} catch(FileNotFoundException e) {
			return false;
		}
	}
	
	private static String getAnonDownloadLink(String anonLink) throws IOException {
		String html = SocketConnection.getURLSource(anonLink);
		int linkIndex = html.indexOf("https://cdn-");
		
		String link = html.substring(linkIndex).split("\"")[0];
		
		return link;
	}
}