package dev;

import dev.command.StartupCommand;
import dev.option.InvalidOptionException;
import dev.option.Options;
import main.Application;

import static dev.option.Option.*;

public class Main {
   public static void main(String[] args) {
      try {
         parseOptions(args);
         new Application(new StartupCommand());
      } catch (InvalidOptionException e) {
         System.out.println("Error: Invalid option: " + e.getName());
         System.exit(1);
      }
   }

   private static void parseOptions(String[] args) throws InvalidOptionException {
      Options opts = new Options();
      opts.add("--destroy", DESTROY);
      opts.add("--sample", SAMPLE);
      opts.parse(args);
   }
}
