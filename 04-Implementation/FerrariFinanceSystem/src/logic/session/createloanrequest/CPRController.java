package logic.session.createloanrequest;

import com.ferrari.finances.dk.rki.Rating;
import domain.Identity;
import exceptions.InvalidCPRException;
import exceptions.ValueRequiredException;

import java.util.function.Consumer;

public interface CPRController {
   Identity getIdentity();

   Rating getCreditRating();

   void fetchCreditRating(Consumer<Rating> resultConsumer,
                          Consumer<Throwable> exceptionConsumer);

   void specifyCPR(String cpr) throws
           InvalidCPRException, ValueRequiredException;

   // Validation
   ///////////////

   String validateCPR(String cpr) throws
           InvalidCPRException, ValueRequiredException;

   String getPartialCPRPattern();
}
