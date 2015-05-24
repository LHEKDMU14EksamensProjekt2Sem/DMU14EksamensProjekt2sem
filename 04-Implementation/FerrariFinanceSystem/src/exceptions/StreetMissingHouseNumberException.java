package exceptions;

public class StreetMissingHouseNumberException extends Exception {
   public StreetMissingHouseNumberException(String street) {
      super("Street is missing a house number: " + street);
   }
}
