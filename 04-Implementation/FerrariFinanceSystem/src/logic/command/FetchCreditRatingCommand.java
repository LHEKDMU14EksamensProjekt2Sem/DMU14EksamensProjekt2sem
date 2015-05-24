package logic.command;

import com.ferrari.finances.dk.rki.CreditRator;
import com.ferrari.finances.dk.rki.Rating;
import util.command.Command;

public class FetchCreditRatingCommand implements Command<Rating> {
   private final String cpr;

   public FetchCreditRatingCommand(String cpr) {
      this.cpr = cpr;
   }

   @Override
   public Rating execute() throws Exception {
      return CreditRator.i().rate(cpr);
   }
}
