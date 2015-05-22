package logic.session.requestloan;

import com.ferrari.finances.dk.rki.Rating;
import domain.Customer;
import domain.Identity;
import util.command.Receiver;

import java.util.Optional;

public interface CPRController {
   Identity getIdentity();

   Customer getCustomer();

   Rating getCreditRating();

   void specifyCPR(String cpr);

   void fetchCustomer(Receiver<Optional<Customer>> resultReceiver,
                      Receiver<Exception> faultReceiver);

   void fetchCreditRating(Receiver<Rating> resultReceiver,
                          Receiver<Exception> faultReceiver);
}
