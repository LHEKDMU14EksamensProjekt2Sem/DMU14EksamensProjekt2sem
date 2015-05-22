package util.command;

@FunctionalInterface
public interface Receiver<T> {
   void receive(T object);
}
