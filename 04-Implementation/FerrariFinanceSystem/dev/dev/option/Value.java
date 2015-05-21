package dev.option;

public interface Value<T> {
   T get();

   void set(T value);
}
