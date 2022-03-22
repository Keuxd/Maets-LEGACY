package maets.download;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public abstract class CloudStorageDownloader {
	
	protected CloudStorageDownloader() {}
	
	protected static void downloadFromUrl(String link, String localFilePath) throws IOException {
		URL url = new URL(link);
		SizeScaleManager ssm = new SizeScaleManager(url.openConnection().getContentLengthLong());

		try (InputStream in = url.openStream();
			BufferedInputStream bis = new BufferedInputStream(in);
	         
			FileOutputStream fos = new FileOutputStream(localFilePath)) {
			 	byte[] data = new byte[1024];
			 	int count;
	            
			 	while ((count = bis.read(data, 0, 1024)) != -1) {
			 		fos.write(data, 0, count);
			 		ssm.add(count);
			 		System.out.println(ssm.getSize());
			 	}
	        }
	}
}