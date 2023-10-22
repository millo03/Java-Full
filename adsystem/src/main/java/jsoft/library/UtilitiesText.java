package jsoft.library;

public class UtilitiesText {// Utilities_Helper

	public static boolean isValidValue(String value) {
		return value != null && value.length() > 0;
	}

	public static boolean isValidPass(String password, String confirmPassword) {
		if (password != null && confirmPassword != null) {
			if (password.length() > 0 && confirmPassword.length() > 0) {
				return password.length() >= 6 && password.equals(confirmPassword);
			}
		}
		return false;
	}
}
