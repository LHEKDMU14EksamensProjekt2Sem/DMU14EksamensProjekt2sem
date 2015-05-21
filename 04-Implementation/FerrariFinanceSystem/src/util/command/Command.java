package util.command;

@FunctionalInterface
public interface Command {
   void execute() throws Exception;
}
