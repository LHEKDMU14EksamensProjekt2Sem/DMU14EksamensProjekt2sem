package exceptions;

public class StreetMissingHouseNumberException extends ValidationException {
   public StreetMissingHouseNumberException(String street) {
      super("Street is missing a house number: " + street);
   }
}
