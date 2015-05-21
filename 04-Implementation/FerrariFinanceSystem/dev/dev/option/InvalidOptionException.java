package dev.option;

public class InvalidOptionException extends Exception {
   private String name;

   public InvalidOptionException(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
}
