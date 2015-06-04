package util.session;

public interface SessionPresenter<F, T> {
   F getFacade();

   void go(T viewToken);
}
