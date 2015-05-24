package exceptions;

public class ValueRequiredException extends Exception {
   public ValueRequiredException(String name) {
      super("Missing required value: " + name);
   }
}
