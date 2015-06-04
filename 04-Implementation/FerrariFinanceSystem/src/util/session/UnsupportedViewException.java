package util.session;

public class UnsupportedViewException extends RuntimeException {
   public UnsupportedViewException(Object viewToken) {
      super(viewToken.toString());
   }
}
