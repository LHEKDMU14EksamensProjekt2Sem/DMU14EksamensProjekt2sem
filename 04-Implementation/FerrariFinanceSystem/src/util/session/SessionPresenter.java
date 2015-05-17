package util.session;

public interface SessionPresenter<V, F> {
   void go(V view);

   F getFacade();
}
