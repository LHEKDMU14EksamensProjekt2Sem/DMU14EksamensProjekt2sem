package logic.session.requestloan;

import com.ferrari.finances.dk.rki.Rating;
import domain.Identity;
import exceptions.InvalidCPRException;
import exceptions.ValueRequiredException;

import java.util.function.Consumer;

public interface CPRController {
   Identity getIdentity();

   Rating getCreditRating();

   String validateCPR(String cpr) throws
           InvalidCPRException, ValueRequiredException;

   void specifyCPR(String cpr) throws
           InvalidCPRException, ValueRequiredException;

   void fetchCreditRating(Consumer<Rating> resultConsumer,
                          Consumer<Throwable> exceptionConsumer);
}
