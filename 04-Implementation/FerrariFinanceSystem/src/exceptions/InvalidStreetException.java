package exceptions;

public class InvalidStreetException extends ValidationException {
   public InvalidStreetException(String street) {
      super("Invalid street: " + street);
   }
}
