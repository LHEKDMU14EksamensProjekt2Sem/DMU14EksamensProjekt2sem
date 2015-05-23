package logic.session.requestloan;

import com.ferrari.finances.dk.rki.Rating;
import domain.Customer;
import domain.Identity;

import java.util.Optional;
import java.util.function.Consumer;

public interface CPRController {
   Identity getIdentity();

   Rating getCreditRating();

   void specifyCPR(String cpr);

   void fetchCustomer(Consumer<Optional<Customer>> resultConsumer,
                      Consumer<Throwable> exceptionConsumer);

   void fetchCreditRating(Consumer<Rating> resultConsumer,
                          Consumer<Throwable> exceptionConsumer);
}
