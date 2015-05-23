package logic.session.requestloan;

import com.ferrari.finances.dk.rki.Rating;
import domain.Customer;
import domain.Identity;
import logic.command.FetchCreditRatingCommand;
import util.command.SwingCommand;

import java.util.Optional;
import java.util.function.Consumer;

public class CPRControllerImpl implements CPRController {
   private final RequestLoanFacade facade;
   private final Identity identity;
   private Rating creditRating;

   public CPRControllerImpl(RequestLoanFacade facade) {
      this.facade = facade;
      identity = new Identity();
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
      return cpr.matches("[0-9]{10}|[0-9]{6}-[0-9]{4}");
   }

   @Override
   public void specifyCPR(String cpr) {
      // Normalize
      cpr = cpr.replace("-", "");
      identity.setCPR(cpr);
   }

   @Override
   public void fetchCustomer(Consumer<Optional<Customer>> resultConsumer,
                             Consumer<Throwable> exceptionConsumer) {
      // TODO
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
