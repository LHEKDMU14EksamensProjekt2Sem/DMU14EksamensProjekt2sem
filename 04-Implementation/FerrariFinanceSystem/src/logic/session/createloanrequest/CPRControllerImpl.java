package logic.session.createloanrequest;

import com.ferrari.finances.dk.rki.Rating;
import domain.Identity;
import domain.Person;
import exceptions.InvalidCPRException;
import exceptions.ValueRequiredException;
import logic.command.FetchCreditRatingCommand;
import logic.session.createloanrequest.validation.CPRValidator;
import logic.session.createloanrequest.validation.CPRValidatorImpl;
import util.swing.SwingCommand;

import java.util.function.Consumer;

public class CPRControllerImpl implements CPRController {
   private final CreateLoanRequestFacade facade;
   private final CPRValidator validator;
   private final Identity identity;
   private Rating creditRating;

   public CPRControllerImpl(CreateLoanRequestFacade facade) {
      this.facade = facade;
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
   public void fetchCreditRating(Consumer<Rating> resultConsumer,
                                 Consumer<Throwable> exceptionConsumer) {
      new SwingCommand<>(
              new FetchCreditRatingCommand(identity.getCPR()),
              r -> {
                 creditRating = r;
                 resultConsumer.accept(r);
              },
              exceptionConsumer::accept
      ).execute();
   }

   @Override
   public void specifyCPR(String cpr) throws
           InvalidCPRException, ValueRequiredException {
      identity.setCPR(validator.validateCPR(cpr));
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
