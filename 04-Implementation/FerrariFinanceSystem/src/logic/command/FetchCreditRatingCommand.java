package logic.command;

import com.ferrari.finances.dk.rki.CreditRator;
import com.ferrari.finances.dk.rki.Rating;
import util.command.AsyncCommand;
import util.command.Callback;

import java.util.concurrent.Executor;

public class FetchCreditRatingCommand extends AsyncCommand {
   private final String cpr;
   private final Callback<Rating, Void> callback;

   public FetchCreditRatingCommand(Executor executor, String cpr,
                                   Callback<Rating, Void> callback) {
      super(executor);
      this.cpr = cpr;
      this.callback = callback;
   }

   @Override
   public void run() {
      callback.success(CreditRator.i().rate(cpr));
   }
}
