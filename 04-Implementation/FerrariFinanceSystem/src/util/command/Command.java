package util.command;

@FunctionalInterface
public interface Command<R> {
   R execute() throws Exception;
}
