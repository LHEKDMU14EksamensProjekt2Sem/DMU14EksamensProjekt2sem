package util.command;

import javax.swing.SwingWorker;
import java.util.concurrent.ExecutionException;

public class SwingCommand<R> extends SwingWorker<R, Void> {
   private final Command<R> command;
   private final Receiver<R> resultReceiver;
   private final Receiver<Throwable> exceptionReceiver;

   public SwingCommand(Command<R> command,
                       Receiver<R> resultReceiver,
                       Receiver<Throwable> exceptionReceiver) {
      this.command = command;
      this.resultReceiver = resultReceiver;
      this.exceptionReceiver = exceptionReceiver;
   }

   @Override
   protected R doInBackground() throws Exception {
      return command.execute();
   }

   @Override
   public void done() {
      try {
         resultReceiver.receive(get());
      } catch (ExecutionException e) {
         exceptionReceiver.receive(e.getCause());
      } catch (InterruptedException ignore) {
         // No-op
      }
   }
}
