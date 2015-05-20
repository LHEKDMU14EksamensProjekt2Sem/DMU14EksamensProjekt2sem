package dev.option;

import main.Environment;

public class Options {
   public static void parse(String[] args) {
      for (String arg : args) {
         // Skip if not an option
         if (!arg.startsWith("--"))
            continue;

         try {
            Option opt = Option.valueOf(
                    arg.substring(2).toUpperCase());

            switch (opt) {
               case DEBUG:
                  Environment.DEBUG = true;
                  break;
            }
         } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid option: " + arg);
         }
      }
   }
}
