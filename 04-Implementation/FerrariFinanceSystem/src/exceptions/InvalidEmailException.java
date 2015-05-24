package exceptions;

public class InvalidEmailException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidEmailException(String email) {
		super("Invalid email: " + email);
	}
}
