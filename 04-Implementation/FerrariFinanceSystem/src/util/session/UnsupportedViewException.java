package util.session;

public class UnsupportedViewException extends RuntimeException {
   public UnsupportedViewException(Object view) {
      super(view.toString());
   }
}
