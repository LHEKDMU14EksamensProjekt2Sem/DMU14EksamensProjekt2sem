package logic.command;

import com.ferrari.finances.dk.rki.CreditRator;
import com.ferrari.finances.dk.rki.Rating;

import java.util.concurrent.Callable;

public class FetchCreditRatingCommand implements Callable<Rating> {
   private final String cpr;

   public FetchCreditRatingCommand(String cpr) {
      this.cpr = cpr;
   }

   @Override
   public Rating call() throws Exception {
      return CreditRator.i().rate(cpr);
   }
}
