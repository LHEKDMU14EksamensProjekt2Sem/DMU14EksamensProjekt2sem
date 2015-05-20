package dev;

import main.Application;
import dev.option.Options;

public class Main {
   public static void main(String[] args) {
      Options.parse(args);
      new Application();
   }
}
