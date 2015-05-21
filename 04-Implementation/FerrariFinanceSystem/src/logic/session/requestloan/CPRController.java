package logic.session.requestloan;

import com.ferrari.finances.dk.rki.Rating;
import domain.Customer;
import domain.Identity;
import util.command.Callback;

import java.sql.SQLException;
import java.util.Optional;

public interface CPRController {
   Identity getIdentity();

   Customer getCustomer();

   Rating getCreditRating();

   void specifyCPR(String cpr);

   void fetchCustomer(Callback<Optional<Customer>, SQLException> callback);

   void fetchCreditRating(Callback<Rating, Void> callback);
}
