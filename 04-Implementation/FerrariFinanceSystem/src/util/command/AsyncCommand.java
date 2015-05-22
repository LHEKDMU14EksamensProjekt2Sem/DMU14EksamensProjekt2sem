package util.command;

@FunctionalInterface
public interface AsyncCommand extends Command, Runnable {
   @Override
   void execute();

   @Override
   default void run() {
      execute();
   }
}
