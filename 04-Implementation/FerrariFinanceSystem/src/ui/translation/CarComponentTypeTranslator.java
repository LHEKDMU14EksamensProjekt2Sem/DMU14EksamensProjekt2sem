package ui.translation;

import domain.CarComponentType;

public class CarComponentTypeTranslator {
   public static String translate(CarComponentType type) {
      switch (type) {
         case PAINT:
            return "Lak";
         case RADIO:
            return "Radio";
         case ENGINE:
            return "Motor";
         case RIMS:
            return "Fælge";
         default:
            return type.toString();
      }
   }
}
