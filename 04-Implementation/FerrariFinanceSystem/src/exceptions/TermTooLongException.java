package exceptions;

public class TermTooLongException extends Exception {
   public TermTooLongException(int term) {
      super("Term too long: " + term);
   }
}
