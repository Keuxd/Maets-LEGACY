package maets.mega;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class CMD {
	
	private String lastOutputLog;
	
	public int executeCommandWithLog(String command) {
	    try {
	        log("\nRunning -> " + command);
	        
	        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", command);
	        Process process = pb.start();
	        
	        logOutput(process.getErrorStream());
	        logOutput(process.getInputStream());
	        
	        return process.waitFor();
	    } catch (IOException | InterruptedException e) {
	        e.printStackTrace();
	        return 1;
	    }
	}

	private void logOutput(InputStream inputStream) {
	    new Thread(() -> {
	        Scanner scanner = new Scanner(inputStream, "UTF-8");
	        while(scanner.hasNextLine()) {
	            synchronized (this) {
	                log(scanner.nextLine());
	            }
	        }
	        scanner.close();
	    }).start();
	}

	private synchronized void log(String message) {
		this.lastOutputLog = message;
	    System.out.println(message);
	}
	
	protected int executeCommand(String command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
			return process.waitFor();
	    } catch (IOException | InterruptedException e) {
	        e.printStackTrace();
	        return 1;
	    }
	}
	
	public String getOutputLog() {
		return this.lastOutputLog;
	}
}