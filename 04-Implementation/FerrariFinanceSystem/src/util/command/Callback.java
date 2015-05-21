package util.command;

public interface Callback<R, E> {
   void success(R result);

   void failure(E exception);
}
