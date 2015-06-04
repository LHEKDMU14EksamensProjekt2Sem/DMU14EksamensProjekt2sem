package exceptions;

public class InvalidPhoneException extends ValidationException {
   public InvalidPhoneException(String phone) {
      super("Invalid phone: " + phone);
   }
}
