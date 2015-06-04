package util.session;

public interface SessionFacade<T> {
   T getViewToken();

   void setViewToken(T token);
}
