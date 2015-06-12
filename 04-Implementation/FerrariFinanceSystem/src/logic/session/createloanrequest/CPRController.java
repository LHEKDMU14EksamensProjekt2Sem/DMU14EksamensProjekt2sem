package logic.session.createloanrequest;

import com.ferrari.finances.dk.rki.Rating;
import domain.Customer;
import domain.Identity;
import exceptions.InvalidCPRException;
import exceptions.ValueRequiredException;

import java.util.Optional;
import java.util.function.Consumer;

public interface CPRController {
   Identity getIdentity();

   Rating getCreditRating();

   void specifyCPR(String cpr,
                   Consumer<Optional<Customer>> resultConsumer,
                   Consumer<Throwable> exceptionConsumer) throws
           InvalidCPRException, ValueRequiredException;

   void fetchCreditRating(Consumer<Rating> resultConsumer,
                          Consumer<Throwable> exceptionConsumer);

   // Validation
   ///////////////

   String validateCPR(String cpr) throws
           InvalidCPRException, ValueRequiredException;

   String getPartialCPRPattern();
}
