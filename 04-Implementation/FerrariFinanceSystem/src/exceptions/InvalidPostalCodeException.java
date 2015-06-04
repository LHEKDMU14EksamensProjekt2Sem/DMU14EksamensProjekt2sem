package exceptions;

public class InvalidPostalCodeException extends ValidationException {
   public InvalidPostalCodeException(String postalCode) {
      super("Invalid postal code: " + postalCode);
   }
}
