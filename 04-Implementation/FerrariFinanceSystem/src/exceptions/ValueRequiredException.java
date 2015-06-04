package exceptions;

public class ValueRequiredException extends ValidationException {
   public ValueRequiredException(String name) {
      super("Missing required value: " + name);
   }
}
