package exceptions;

public class InvalidCPRException extends Exception {
   public InvalidCPRException(String cpr) {
      super("Invalid CPR: " + cpr);
   }
}
