package exceptions;

public class ValidationException extends Exception {
   public ValidationException(String message) {
      super(message);
   }

   /**
    * This method is a no-op. It has been overridden to simply
    * return {@code null} to prevent the relatively expensive
    * operation of filling in the stack trace. This exception
    * concerns input validation in an HCI situation rendering
    * the stack trace irrelevant.
    *
    * @return null
    */
   @Override
   public Throwable fillInStackTrace() {
      return null;
   }
}
