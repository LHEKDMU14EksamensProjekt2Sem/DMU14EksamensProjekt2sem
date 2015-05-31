package logic.session.requestloan.validation;

import exceptions.InvalidEmailException;
import exceptions.InvalidNameException;
import exceptions.InvalidPhoneException;
import exceptions.InvalidPostalCodeException;
import exceptions.InvalidStreetException;
import exceptions.StreetMissingHouseNumberException;

import java.util.regex.Matcher;

public interface CustomerDetailsValidator {
   /**
    * Validates any first or last name.
    *
    * @param name a first or last name
    * @throws InvalidNameException
    */
   String validateName(String name) throws InvalidNameException;

   /**
    * Validates a Danish street. Must include a house number.
    *
    * @param street a Danish street
    * @throws InvalidStreetException
    * @throws StreetMissingHouseNumberException
    */
   String validateStreet(String street) throws
           InvalidStreetException, StreetMissingHouseNumberException;

   /**
    * Validates a Danish postal code. Must be 4 digits exactly. Any number
    * less than 1000 is invalid.
    *
    * @param postalCode a Danish postal code
    * @throws InvalidPostalCodeException
    */
   int validatePostalCode(String postalCode) throws InvalidPostalCodeException;

   /**
    * Validates a Danish phone number. Must be 8 digits exactly. Including
    * country code +45 is allowed. Returns the 8 digit phone number.
    *
    * @param phone a Danish phone number
    * @return the 8 digit phone number
    * @throws InvalidPhoneException
    */
   int validatePhone(String phone) throws InvalidPhoneException;

   /**
    * Validates an email address.
    *
    * @param email an email address
    * @throws InvalidEmailException
    */
   String validateEmail(String email) throws InvalidEmailException;
}
