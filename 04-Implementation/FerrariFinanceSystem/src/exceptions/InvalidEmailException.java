package exceptions;

public class InvalidEmailException extends ValidationException {
   public InvalidEmailException(String email) {
      super("Invalid email: " + email);
   }
}
