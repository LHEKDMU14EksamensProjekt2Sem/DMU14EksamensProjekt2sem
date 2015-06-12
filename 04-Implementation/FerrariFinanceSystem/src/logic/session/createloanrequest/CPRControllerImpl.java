package logic.session.createloanrequest;

import com.ferrari.finances.dk.rki.Rating;
import domain.Customer;
import domain.Identity;
import domain.Person;
import exceptions.InvalidCPRException;
import exceptions.ValueRequiredException;
import logic.session.createloanrequest.validation.CPRValidator;
import logic.session.createloanrequest.validation.CPRValidatorImpl;
import logic.session.main.MainFacade;

import java.util.Optional;
import java.util.function.Consumer;

public class CPRControllerImpl implements CPRController {
   private final CreateLoanRequestFacade facade;
   private final MainFacade mainFacade;
   private final CPRValidator validator;
   private final Identity identity;
   private Rating creditRating;

   public CPRControllerImpl(CreateLoanRequestFacade facade, MainFacade mainFacade) {
      this.facade = facade;
      this.mainFacade = mainFacade;
      validator = new CPRValidatorImpl();
      identity = new Identity();
      identity.setPerson(new Person());
   }

   @Override
   public Identity getIdentity() {
      return identity;
   }

   @Override
   public Rating getCreditRating() {
      return creditRating;
   }

   @Override
   public void specifyCPR(String cpr,
                          Consumer<Optional<Customer>> resultConsumer,
                          Consumer<Throwable> exceptionConsumer) throws
           InvalidCPRException, ValueRequiredException {
      identity.setCPR(validator.validateCPR(cpr));
      facade.fetchCustomer(resultConsumer, exceptionConsumer);
   }

   @Override
   public void fetchCreditRating(Consumer<Rating> resultConsumer,
                                 Consumer<Throwable> exceptionConsumer) {
      mainFacade.fetchCreditRating(
              identity,
              r -> {
                 creditRating = r;
                 resultConsumer.accept(r);
              },
              exceptionConsumer::accept);
   }

   // Validation
   ///////////////

   @Override
   public String validateCPR(String cpr) throws
           InvalidCPRException, ValueRequiredException {
      cpr = cpr.trim();
      if (cpr.isEmpty())
         throw new ValueRequiredException("CPR");

      return validator.validateCPR(cpr);
   }

   @Override
   public String getPartialCPRPattern() {
      return validator.getPartialCPRPattern();
   }
}
