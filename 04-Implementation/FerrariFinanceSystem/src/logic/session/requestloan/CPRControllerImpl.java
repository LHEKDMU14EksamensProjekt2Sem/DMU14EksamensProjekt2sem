package logic.session.requestloan;

import com.ferrari.finances.dk.rki.Rating;
import domain.Customer;
import domain.Identity;
import logic.command.FetchCreditRatingCommand;
import util.command.Receiver;
import util.command.SwingCommand;

import java.util.Optional;

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
   public void specifyCPR(String cpr) {
      identity.setCPR(cpr);
   }

   @Override
   public void fetchCustomer(Receiver<Optional<Customer>> resultReceiver,
                             Receiver<Throwable> exceptionReceiver) {
      // TODO
   }

   @Override
   public void fetchCreditRating(Receiver<Rating> resultReceiver,
                                 Receiver<Throwable> exceptionReceiver) {
      new SwingCommand<>(
              new FetchCreditRatingCommand(identity.getCPR()),
              r -> {
                 creditRating = r;
                 resultReceiver.receive(r);
              },
              exceptionReceiver::receive
      ).execute();
   }
}
