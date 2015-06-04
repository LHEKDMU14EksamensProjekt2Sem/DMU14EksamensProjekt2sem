package exceptions;

public class InvalidNameException extends ValidationException {
   public InvalidNameException(String name) {
      super("Invalid name: " + name);
   }
}
