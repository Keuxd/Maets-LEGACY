package maets.screen.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import maets.mega.Mega;
import maets.mega.exceptions.MegaException;
import maets.screen.login.exceptions.LoginEmptyEmailException;
import maets.screen.login.exceptions.LoginEmptyPasswordException;
import maets.screen.login.exceptions.LoginException;
import maets.screen.login.exceptions.LoginInvalidEmailFormatException;

public class LoginForm {
	
	private String email;
	private String password;

	public LoginForm(String email, char[] password) {
		this.email = email;
		this.password = new String(password);
	}
	
	private void validate() throws LoginException {
		if(email.isEmpty())
			throw new LoginEmptyEmailException();

		if(!isEmailValid())
			throw new LoginInvalidEmailFormatException();

		if(password.isEmpty())
			throw new LoginEmptyPasswordException();
	} 
	
	private boolean isEmailValid() {
		String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Matcher matcher = Pattern.compile(regex).matcher(this.email);
		return matcher.matches();
	}
	
	protected void login() throws LoginException, MegaException {
		validate();
		Mega.login(this.email, this.password);
	}
}