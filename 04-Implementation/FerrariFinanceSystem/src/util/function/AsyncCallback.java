package util.function;

import util.command.Response;

@FunctionalInterface
public interface AsyncCallback<R, E> {
   void call(Response<R, E> response);
}
