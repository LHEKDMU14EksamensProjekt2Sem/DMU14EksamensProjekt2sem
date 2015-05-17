package util.command;

import java.util.Optional;

public class Response<R, E> {
   private Optional<R> optional;
   private E exception;

   public Response() {
      optional = Optional.empty();
   }

   public Optional<R> getOptional() {
      return optional;
   }

   public void setOptional(Optional<R> optional) {
      this.optional = optional;
   }

   public E getException() {
      return exception;
   }

   public void setException(E exception) {
      this.exception = exception;
   }

   public boolean success() {
      return (exception == null);
   }
}
