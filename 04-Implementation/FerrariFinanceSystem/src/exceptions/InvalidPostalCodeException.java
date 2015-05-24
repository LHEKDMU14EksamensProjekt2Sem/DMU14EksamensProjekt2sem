package exceptions;

public class InvalidPostalCodeException extends Exception {
   public InvalidPostalCodeException(String postalCode) {
      super("Invalid postal code: " + postalCode);
   }
}
