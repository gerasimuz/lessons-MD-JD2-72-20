package by.htp.ellib.service.validation;

public class GredentionalValidator {

	public static boolean isCorrect(String login, String password) {
		return isLogicCorrect(login) && isPasswordCorrect(login);

	}

	private static boolean isLogicCorrect(String login) {
		if (login.length() <= 2) {
			return false;
		}
		return true;
	}

	private static boolean isPasswordCorrect(String password) {
		if (password.length() <= 2) {
			return false;
		}
		return true;
	}

}
