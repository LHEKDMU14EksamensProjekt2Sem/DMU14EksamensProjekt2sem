package logic.session.main;

import com.ferrari.finances.dk.rki.Rating;
import domain.Identity;
import logic.format.GeneralDateFormat;
import logic.format.GeneralNumberFormat;

import java.util.function.Consumer;

public interface MainController {
   GeneralNumberFormat getGeneralNumberFormat();

   GeneralDateFormat getGeneralDateFormat();

   void fetchCreditRating(Identity identity,
                          Consumer<Rating> resultConsumer,
                          Consumer<Throwable> exceptionConsumer);

   void fetchOvernightRate(Consumer<Double> resultConsumer,
                           Consumer<Throwable> exceptionConsumer);
}
