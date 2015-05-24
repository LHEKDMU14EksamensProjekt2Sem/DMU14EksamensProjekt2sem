package exceptions;

public class InvalidPhoneException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidPhoneException(String phone) {
		super("Invalid phone: " + phone);
	}
}
