package logic.session.requestloan;

import com.ferrari.finances.dk.rki.Rating;
import domain.Identity;
import domain.Person;
import logic.command.FetchCreditRatingCommand;
import logic.session.requestloan.validation.CPRValidator;
import logic.session.requestloan.validation.CPRValidatorImpl;
import util.swing.SwingCommand;

import java.util.function.Consumer;

public class CPRControllerImpl implements CPRController {
   private final RequestLoanFacade facade;
   private final CPRValidator validator;
   private final Identity identity;
   private Rating creditRating;

   public CPRControllerImpl(RequestLoanFacade facade) {
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
   public boolean validateCPR(String cpr) {
      return validator.validateCPR(cpr);
   }

   @Override
   public void specifyCPR(String cpr) {
      if (validator.validateCPR(cpr)) {
         // Normalize
         cpr = cpr.replace("-", "");
         identity.setCPR(cpr);
      }
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
}
