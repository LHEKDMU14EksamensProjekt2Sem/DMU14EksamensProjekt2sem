package logic.session.createloanrequest;

import domain.Customer;
import domain.Identity;
import domain.Person;
import domain.PostalCode;
import exceptions.InvalidEmailException;
import exceptions.InvalidNameException;
import exceptions.InvalidPhoneException;
import exceptions.InvalidPostalCodeException;
import exceptions.InvalidStreetException;
import exceptions.StreetMissingHouseNumberException;
import exceptions.ValueRequiredException;
import logic.command.FetchCustomerCommand;
import logic.command.FetchPostalCodeCommand;
import logic.session.createloanrequest.validation.CustomerDetailsValidator;
import logic.session.createloanrequest.validation.CustomerDetailsValidatorImpl;
import util.swing.SwingCommand;

import java.util.Optional;
import java.util.function.Consumer;

public class CustomerDetailsControllerImpl implements CustomerDetailsController {
   private final CreateLoanRequestFacade facade;
   private final CustomerDetailsValidator validator;
   private Customer customer;
   private Person person;

   public CustomerDetailsControllerImpl(CreateLoanRequestFacade facade) {
      this.facade = facade;
      validator = new CustomerDetailsValidatorImpl();

      person = facade.getIdentity().getPerson();
      customer = new Customer();
      customer.setPerson(person);
   }

   @Override
   public Customer getCustomer() {
      return customer;
   }

   @Override
   public void specifyFirstName(String firstName) throws
           InvalidNameException, ValueRequiredException {
      person.setFirstName(validateFirstName(firstName));
   }

   @Override
   public void specifyLastName(String lastName) throws
           InvalidNameException, ValueRequiredException {
      person.setLastName(validateLastName(lastName));
   }

   @Override
   public void specifyStreet(String street) throws
           InvalidStreetException, StreetMissingHouseNumberException, ValueRequiredException {
      person.setStreet(validateStreet(street));
   }

   @Override
   public void specifyPostalCode(String postalCode,
                                 Consumer<Optional<PostalCode>> resultConsumer,
                                 Consumer<Throwable> exceptionConsumer) throws
           InvalidPostalCodeException, ValueRequiredException {
      int value = validatePostalCode(postalCode);

      new SwingCommand<>(
              new FetchPostalCodeCommand(value),
              r -> {
                 if (r.isPresent())
                    person.setPostalCode(r.get());

                 resultConsumer.accept(r);
              },
              exceptionConsumer::accept
      ).execute();
   }

   @Override
   public void specifyPhone(String phone) throws
           InvalidPhoneException, ValueRequiredException {
      person.setPhone(validatePhone(phone));
   }

   @Override
   public void specifyEmail(String email) throws
           InvalidEmailException, ValueRequiredException {
      person.setEmail(validateEmail(email));
   }

   @Override
   public void fetchCustomer(Consumer<Optional<Customer>> resultConsumer,
                             Consumer<Throwable> exceptionConsumer) {
      Identity identity = facade.getIdentity();
      new SwingCommand<>(
              new FetchCustomerCommand(identity),
              r -> {
                 if (r.isPresent()) {
                    customer = r.get();
                    identity.setPerson(customer.getPerson());
                 }

                 resultConsumer.accept(r);
              },
              exceptionConsumer
      ).execute();
   }

   // Validation
   ///////////////

   @Override
   public String validateFirstName(String firstName) throws
           InvalidNameException, ValueRequiredException {
      firstName = firstName.trim();
      if (firstName.isEmpty())
         throw new ValueRequiredException("First name");

      return validator.validateName(firstName);
   }

   @Override
   public String validateLastName(String lastName) throws
           InvalidNameException, ValueRequiredException {
      lastName = lastName.trim();
      if (lastName.isEmpty())
         throw new ValueRequiredException("Last name");

      return validator.validateName(lastName);
   }

   @Override
   public String validateStreet(String street) throws
           InvalidStreetException, StreetMissingHouseNumberException, ValueRequiredException {
      street = street.trim();
      if (street.isEmpty())
         throw new ValueRequiredException("Street");

      return validator.validateStreet(street);
   }

   @Override
   public int validatePostalCode(String postalCode) throws
           InvalidPostalCodeException, ValueRequiredException {
      postalCode = postalCode.trim();
      if (postalCode.isEmpty())
         throw new ValueRequiredException("Postal code");

      return validator.validatePostalCode(postalCode);
   }

   @Override
   public int validatePhone(String phone) throws
           InvalidPhoneException, ValueRequiredException {
      phone = phone.trim();
      if (phone.isEmpty())
         throw new ValueRequiredException("Phone");

      return validator.validatePhone(phone);
   }

   @Override
   public String validateEmail(String email) throws
           InvalidEmailException, ValueRequiredException {
      email = email.trim();
      if (email.isEmpty())
         throw new ValueRequiredException("Email");

      return validator.validateEmail(email);
   }

   @Override
   public String getPartialPhonePattern() {
      return validator.getPartialPhonePattern();
   }
}
