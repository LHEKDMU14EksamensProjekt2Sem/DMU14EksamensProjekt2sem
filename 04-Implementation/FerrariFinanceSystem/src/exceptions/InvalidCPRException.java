package exceptions;

public class InvalidCPRException extends ValidationException {
   public InvalidCPRException(String cpr) {
      super("Invalid CPR: " + cpr);
   }
}
