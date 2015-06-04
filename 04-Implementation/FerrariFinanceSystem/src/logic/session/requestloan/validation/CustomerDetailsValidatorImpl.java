package logic.session.requestloan.validation;

import exceptions.InvalidEmailException;
import exceptions.InvalidNameException;
import exceptions.InvalidPhoneException;
import exceptions.InvalidPostalCodeException;
import exceptions.InvalidStreetException;
import exceptions.StreetMissingHouseNumberException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerDetailsValidatorImpl implements CustomerDetailsValidator {
   private static final String
           NAME = "([a-zæøåA-ZÆØÅ-]\\.?\\s*)+",
           STREET = "([a-zæøåA-ZÆØÅ-]\\.?\\s*\\d*(\\.|,)?\\s*)+",
           STREET_HOUSE_NUMBER = ".*\\d+.*",
           POSTAL_CODE = "[1-9]\\d{3}",
           PHONE = "(?:\\+45\\s*)?(\\d{8})",
           PHONE_PARTIAL = "\\d{0,8}|\\+((?<=\\+)4)?((?<=\\+4)5)?((?<=\\+45)\\s?\\d{0,8})?",
           EMAIL = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";

   @Override
   public String validateName(String name) throws InvalidNameException {
      if (!name.matches(NAME))
         throw new InvalidNameException(name);

      return name;
   }

   @Override
   public String validateStreet(String street) throws
           InvalidStreetException, StreetMissingHouseNumberException {
      if (!street.matches(STREET))
         throw new InvalidStreetException(street);

      if (!street.matches(STREET_HOUSE_NUMBER))
         throw new StreetMissingHouseNumberException(street);

      return street;
   }

   @Override
   public int validatePostalCode(String postalCode) throws InvalidPostalCodeException {
      if (!postalCode.matches(POSTAL_CODE))
         throw new InvalidPostalCodeException(postalCode);

      return Integer.parseInt(postalCode);
   }

   @Override
   public int validatePhone(String phone) throws InvalidPhoneException {
      Pattern p = Pattern.compile(PHONE);
      Matcher m = p.matcher(phone);

      if (!m.matches())
         throw new InvalidPhoneException(phone);

      return Integer.parseInt(m.group(1));
   }

   @Override
   public String validateEmail(String email) throws InvalidEmailException {
      if (!email.matches(EMAIL))
         throw new InvalidEmailException(email);

      return email;
   }

   @Override
   public String getPartialPhonePattern() {
      return PHONE_PARTIAL;
   }
}
