package maets.drive;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Drive {
	
	private static SizeScaleManager ssm;
	
    public static void downloadFile(String fileId, String fileName) throws IOException {    	
    	URL url = new URL("https://drive.google.com/uc?export=download&id=" + fileId + "&confirm=t");
    	long fileSize = url.openConnection().getContentLengthLong(); // Server might return a -1 for error
    	
    	if(fileSize == -1)
    		throw new IOException("File length has size -1, Invalid input file");
    	
    	ssm = new SizeScaleManager(fileSize);
    	
        try (InputStream in = url.openStream();
                BufferedInputStream bis = new BufferedInputStream(in);
                FileOutputStream fos = new FileOutputStream(fileName)) {
        	
        	byte[] data = new byte[1024];
            int count;
            while ((count = bis.read(data, 0, 1024)) != -1) {
                fos.write(data, 0, count);
                ssm.add(count);
                System.out.println(ssm.getSize());
            }
            
            ssm = null;
        }
    }
}
