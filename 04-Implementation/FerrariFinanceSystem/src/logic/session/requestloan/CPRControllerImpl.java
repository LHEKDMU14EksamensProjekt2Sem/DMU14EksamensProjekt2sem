package logic.session.requestloan;

import com.ferrari.finances.dk.rki.Rating;
import domain.Customer;
import domain.Identity;
import logic.command.FetchCreditRatingCommand;
import util.command.Receiver;

import java.util.Optional;

public class CPRControllerImpl implements CPRController {
   private final RequestLoanSessionFacade facade;
   private final Identity identity;
   private Customer customer;
   private Rating creditRating;

   public CPRControllerImpl(RequestLoanSessionFacade facade) {
      this.facade = facade;
      identity = new Identity();
   }

   @Override
   public Identity getIdentity() {
      return identity;
   }

   @Override
   public Customer getCustomer() {
      return customer;
   }

   @Override
   public Rating getCreditRating() {
      return creditRating;
   }

   @Override
   public void specifyCPR(String cpr) {
      identity.setCPR(cpr);
   }

   @Override
   public void fetchCustomer(Receiver<Optional<Customer>> resultReceiver,
                             Receiver<Exception> faultReceiver) {
      // TODO
   }

   @Override
   public void fetchCreditRating(Receiver<Rating> receiver) {
      facade.getExecutor().execute(
              new FetchCreditRatingCommand(identity.getCPR(),
                      r -> {
                         creditRating = r;
                         receiver.receive(r);
                      }
              ));
   }
}
