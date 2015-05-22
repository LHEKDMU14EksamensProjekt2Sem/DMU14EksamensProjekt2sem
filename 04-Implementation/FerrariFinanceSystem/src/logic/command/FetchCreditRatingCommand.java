package logic.command;

import com.ferrari.finances.dk.rki.CreditRator;
import com.ferrari.finances.dk.rki.Rating;
import util.command.AsyncCommand;
import util.command.Receiver;

public class FetchCreditRatingCommand implements AsyncCommand {
   private final String cpr;
   private final Receiver<Rating> resultReceiver;
   private final Receiver<Exception> faultReceiver;

   public FetchCreditRatingCommand(String cpr,
                                   Receiver<Rating> resultReceiver,
                                   Receiver<Exception> faultReceiver) {
      this.cpr = cpr;
      this.resultReceiver = resultReceiver;
      this.faultReceiver = faultReceiver;
   }

   @Override
   public void execute() {
      try {
         resultReceiver.receive(
                 CreditRator.i().rate(cpr));
      } catch (Exception e) {
         faultReceiver.receive(e);
      }
   }
}
