package query;

public abstract class AbstractQuery implements Query {
   private final long timeout;

   public AbstractQuery(long timeout) {
      this.timeout = timeout;
   }

   @Override
   public long getTimeout() {
      return timeout;
   }
}
