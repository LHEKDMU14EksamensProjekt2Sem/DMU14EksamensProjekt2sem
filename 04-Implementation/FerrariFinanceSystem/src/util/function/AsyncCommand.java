package util.function;

@FunctionalInterface
public interface AsyncCommand<R, E> {
   void execute(AsyncCallback<R, E> callback);
}
