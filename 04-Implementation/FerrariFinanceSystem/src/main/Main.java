package main;

import logic.command.StartupCommand;

public class Main {
   public static void main(String[] args) {
      new Application(new StartupCommand());
   }
}
