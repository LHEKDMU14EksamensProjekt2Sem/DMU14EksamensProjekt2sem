package exceptions;

public class InvalidStreetException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidStreetException(String street) {
		super("Invalid street: " + street);
	}
}
