package util.command;

import java.util.concurrent.Executor;

public abstract class AsyncCommand implements Command, Runnable {
   private final Executor executor;

   protected AsyncCommand(Executor executor) {
      this.executor = executor;
   }

   @Override
   public void execute() {
      executor.execute(this);
   }
}
