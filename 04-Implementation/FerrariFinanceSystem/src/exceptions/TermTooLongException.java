package exceptions;

public class TermTooLongException extends ValidationException {
   public TermTooLongException(int term) {
      super("Term too long: " + term);
   }
}
