package maets.mega;

import maets.mega.exceptions.MegaException;
import maets.mega.exceptions.MegaInvalidArgumentsException;
import maets.mega.exceptions.MegaInvalidLocalPathException;
import maets.mega.exceptions.MegaInvalidRemoteFolderException;
import maets.mega.exceptions.MegaInvalidRemoteNodesException;
import maets.mega.exceptions.MegaInvalidRemotePathException;
import maets.mega.exceptions.MegaInvalidStateException;
import maets.mega.exceptions.MegaLoginFailedException;
import maets.mega.exceptions.MegaNotLoggedInException;
import maets.mega.exceptions.MegaUnconfirmedAccountException;
import maets.mega.exceptions.MegaWrongArgumentsException;

public class Mega {
	
	private static CMD cmd;
	
	private Mega() {}
	
	public static void init() throws MegaException {
		if(cmd == null) {
			cmd = new CMD();
			
			// Opens MegaCmdServer
			handleResponseCode(cmd.executeCommand("megaclient version"), "Server Connection");
			
			// If MegaCmdServer is open the response code will be -2
			handleResponseCode(cmd.executeCommand("megacmdserver"), "MegaCmdServer");
			
			// We can't handle if MegaCmdServer doesn't open :(
		}
	}
	
	public static void quit() throws MegaException {
		handleResponseCode(cmd.executeCommandWithLog("mega-quit"), "Quit");
	}
	
	public static void login(String email, String pwd) throws MegaException {
		System.out.println("\nTrying to login...\nEmail: " + email + "\nPassword: " + pwd + "\n");
		handleResponseCode(cmd.executeCommandWithLog(String.format("mega-login %s %s", email, pwd)), "Login");
	}
	
	public static void logout() throws MegaException {
		handleResponseCode(cmd.executeCommandWithLog("mega-logout"), "Logout");
	}
	
	public static void upload(String localPath, String remotePath, boolean mkdir) throws MegaException {
		String createFolderArgument = (mkdir) ? "-c": "";
		handleResponseCode(cmd.executeCommandWithLog(String.format("mega-put \"%s\" \"%s\" %s", localPath, remotePath, createFolderArgument)), "Upload");
	}
	
	public static void download(String linkOrRemotePath, String localPath) throws MegaException {	
		handleResponseCode(cmd.executeCommandWithLog(String.format("mega-get \"%s\" \"%s\"", linkOrRemotePath, localPath)), "Download");
	}
	
	public static void mkdir(String remotePath) throws MegaException {
		handleResponseCode(cmd.executeCommandWithLog(String.format("mega-mkdir \"%s\" -p", remotePath)), "Create Folder(mkdir)");
	}
	
	public static void move(String srcRemotePath, String dstRemotePath) throws MegaException {
		handleResponseCode(cmd.executeCommandWithLog(String.format("mega-mv \"%s\" \"%s\"", srcRemotePath, dstRemotePath)), "Move File(s)/Folder(s)");
	}
	
	public static void remove(String remotePath) throws MegaException {
		handleResponseCode(cmd.executeCommandWithLog(String.format("mega-rm \"%s\" -r -f", remotePath)), "Remove");
	}
	
	public static boolean isLoggedIn() {
		try {
			handleResponseCode(cmd.executeCommandWithLog(String.format("mega-users")), "Users");
			return true;
		} catch(MegaNotLoggedInException e) {
			return false;
		} catch(MegaException e) {
			e.printStackTrace();
			System.exit(-1);
			return false;
		}
	}
	
	public static String getRemoteFileContent(String remotePath) throws MegaException {
		handleResponseCode(cmd.executeCommandWithLog(String.format("mega-cat \"%s\"", remotePath)), "Print Content(CAT)");
		return cmd.getOutputLog();
	}
	
	public static String getUserName(String whichName) throws MegaException {
		handleResponseCode(cmd.executeCommandWithLog(String.format("mega-userattr %s", whichName)), "UserAttr");
		return cmd.getOutputLog().split("=")[1];
	}
	
	private static void handleResponseCode(int code, String commandName) throws MegaException {
		switch(code) {
			case 0 : System.out.println(commandName + " returned code 0"); return; // <- Everything OK
			case -2 : System.out.println(commandName + " is online, code -2"); return; // <- Server is already running, no prob
			case 2 : throw new MegaInvalidArgumentsException();
			case 9 : throw new MegaLoginFailedException();
			case 13 : throw new MegaUnconfirmedAccountException();
			case 51 : throw new MegaWrongArgumentsException();
			case 53 : {
				if(cmd.getOutputLog().contains("find"))
					throw new MegaInvalidRemoteFolderException();

				if(cmd.getOutputLog().contains("local"))
					throw new MegaInvalidLocalPathException(code);
				
				if(cmd.getOutputLog().contains("Nodes"))
					throw new MegaInvalidRemoteNodesException();
				
				if(cmd.getOutputLog().contains("No such file or directory"))
					throw new MegaInvalidRemotePathException();
				
				if(cmd.getOutputLog().contains("No such directory"))
					throw new MegaException("Resource not found - No such directory - Code: 53");
				
				if(cmd.getOutputLog().contains("Attribute not found"))
					throw new MegaException("Invalid attribute in userattr(prob) - Code: 53");
				
				System.out.println("\nCODE 53 BUT UNKNOWN ERROR\n");
				break;
			}
			case 54 : throw new MegaInvalidStateException();
			case 55 : {
				if(cmd.getOutputLog().contains("valid"))
					throw new MegaInvalidLocalPathException(code);
				
				if(cmd.getOutputLog().contains("Couldn't find file"))
					throw new MegaException("Invalid type - Couldn't find file - Code: 55");
				
				System.out.println("\nCODE 55 BUT NO LOCAL PROBLEM\n");
				break;
			}
			case 57 : throw new MegaNotLoggedInException();
			default : throw new MegaException(code); // <- Oh fuck
		}
	}
}