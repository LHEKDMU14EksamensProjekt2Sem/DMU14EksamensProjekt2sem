package logic.session.requestloan;

import domain.Customer;
import domain.Person;
import domain.PostalCode;
import exceptions.InvalidEmailException;
import exceptions.InvalidNameException;
import exceptions.InvalidPhoneException;
import exceptions.InvalidPostalCodeException;
import exceptions.InvalidStreetException;
import exceptions.StreetMissingHouseNumberException;
import exceptions.ValueRequiredException;
import logic.command.FetchPostalCodeCommand;
import logic.session.requestloan.validation.CustomerDetailsValidator;
import logic.session.requestloan.validation.CustomerDetailsValidatorImpl;
import util.swing.SwingCommand;

import java.util.Optional;
import java.util.function.Consumer;

public class CustomerDetailsControllerImpl implements CustomerDetailsController {
   private final RequestLoanFacade facade;
   private final CustomerDetailsValidator validator;
   private final Customer customer;
   private final Person person;

   public CustomerDetailsControllerImpl(RequestLoanFacade facade) {
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
      String s = firstName.trim();
      if (s.isEmpty())
         throw new ValueRequiredException("First name");

      validator.validateName(s);
      person.setFirstName(s);
   }

   @Override
   public void specifyLastName(String lastName) throws
           InvalidNameException, ValueRequiredException {
      String s = lastName.trim();
      if (s.isEmpty())
         throw new ValueRequiredException("Last name");

      validator.validateName(s);
      person.setLastName(s);
   }

   @Override
   public void specifyStreet(String street) throws
           InvalidStreetException, StreetMissingHouseNumberException, ValueRequiredException {
      String s = street.trim();
      if (s.isEmpty())
         throw new ValueRequiredException("Street");

      validator.validateStreet(s);
      person.setStreet(s);
   }

   @Override
   public void specifyPostalCode(String postalCode,
                                 Consumer<Optional<PostalCode>> resultConsumer,
                                 Consumer<Throwable> exceptionConsumer) throws
           InvalidPostalCodeException, ValueRequiredException {
      String s = postalCode.trim();
      if (s.isEmpty())
         throw new ValueRequiredException("Postal code");

      validator.validatePostalCode(s);
      new SwingCommand<>(
              new FetchPostalCodeCommand(Integer.parseInt(s)),
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
      String s = phone.trim();
      if (s.isEmpty())
         throw new ValueRequiredException("Phone");

      person.setPhone(validator.validatePhone(s));
   }

   @Override
   public void specifyEmail(String email) throws
           InvalidEmailException, ValueRequiredException {
      String s = email.trim();
      if (s.isEmpty())
         throw new ValueRequiredException("Email");

      validator.validateEmail(s);
      person.setEmail(s);
   }

   @Override
   public void fetchCustomer(Consumer<Optional<Customer>> resultConsumer,
                             Consumer<Throwable> exceptionConsumer) {
      // TODO
   }
}
