package util.command;

import javax.swing.SwingWorker;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class SwingCommand<R> extends SwingWorker<R, Void> {
   private final Callable<R> command;
   private final Consumer<R> resultConsumer;
   private final Consumer<Throwable> exceptionConsumer;

   public SwingCommand(Callable<R> command,
                       Consumer<R> resultConsumer,
                       Consumer<Throwable> exceptionConsumer) {
      this.command = command;
      this.resultConsumer = resultConsumer;
      this.exceptionConsumer = exceptionConsumer;
   }

   @Override
   protected R doInBackground() throws Exception {
      return command.call();
   }

   @Override
   public void done() {
      try {
         resultConsumer.accept(get());
      } catch (ExecutionException e) {
         exceptionConsumer.accept(e.getCause());
      } catch (InterruptedException ignore) {
         // No-op
      }
   }
}
