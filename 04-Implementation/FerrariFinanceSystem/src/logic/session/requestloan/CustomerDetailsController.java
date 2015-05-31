package logic.session.requestloan;

import domain.Customer;
import domain.PostalCode;
import exceptions.InvalidEmailException;
import exceptions.InvalidNameException;
import exceptions.InvalidPhoneException;
import exceptions.InvalidPostalCodeException;
import exceptions.InvalidStreetException;
import exceptions.StreetMissingHouseNumberException;
import exceptions.ValueRequiredException;

import java.util.Optional;
import java.util.function.Consumer;

public interface CustomerDetailsController {
   Customer getCustomer();

   void specifyFirstName(String firstName) throws
           InvalidNameException, ValueRequiredException;

   void specifyLastName(String lastName) throws
           InvalidNameException, ValueRequiredException;

   void specifyStreet(String street) throws
           InvalidStreetException, StreetMissingHouseNumberException, ValueRequiredException;

   void specifyPostalCode(String postalCode,
                          Consumer<Optional<PostalCode>> resultConsumer,
                          Consumer<Throwable> exceptionConsumer)
           throws InvalidPostalCodeException, ValueRequiredException;

   void specifyPhone(String phone) throws
           InvalidPhoneException, ValueRequiredException;

   void specifyEmail(String email) throws
           InvalidEmailException, ValueRequiredException;

   void fetchCustomer(Consumer<Optional<Customer>> resultConsumer,
                      Consumer<Throwable> exceptionConsumer);

   // Validation
   ///////////////

   String validateFirstName(String name) throws
           InvalidNameException, ValueRequiredException;

   String validateLastName(String name) throws
           InvalidNameException, ValueRequiredException;

   String validateStreet(String street) throws
           InvalidStreetException, StreetMissingHouseNumberException, ValueRequiredException;

   int validatePostalCode(String postalCode) throws
           InvalidPostalCodeException, ValueRequiredException;

   int validatePhone(String phone) throws
           InvalidPhoneException, ValueRequiredException;

   String validateEmail(String email) throws
           InvalidEmailException, ValueRequiredException;
}
