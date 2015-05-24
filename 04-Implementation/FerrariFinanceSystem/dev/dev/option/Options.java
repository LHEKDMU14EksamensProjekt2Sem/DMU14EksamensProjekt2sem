package dev.option;

import java.util.HashMap;

public class Options {
   private final HashMap<String, Value<Boolean>> opts;

   public Options() {
      opts = new HashMap<>();
   }

   public void add(String name, Value<Boolean> option) {
      opts.put(name, option);
   }

   public void parse(String[] args) throws InvalidOptionException {
      for (String arg : args) {
         Value<Boolean> opt = opts.get(arg.toLowerCase());

         if (opt == null)
            throw new InvalidOptionException(arg);

         opt.set(true);
      }
   }
}
