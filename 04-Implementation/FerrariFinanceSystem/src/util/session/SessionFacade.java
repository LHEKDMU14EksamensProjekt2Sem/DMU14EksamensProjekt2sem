package util.session;

public interface SessionFacade<V> {
   V getView();

   void setView(V view);
}
