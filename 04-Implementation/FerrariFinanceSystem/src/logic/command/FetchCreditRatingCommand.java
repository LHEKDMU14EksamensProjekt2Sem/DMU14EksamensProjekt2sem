package logic.command;

import com.ferrari.finances.dk.rki.CreditRator;
import com.ferrari.finances.dk.rki.Rating;
import util.command.AsyncCommand;
import util.command.Receiver;

public class FetchCreditRatingCommand implements AsyncCommand {
   private final String cpr;
   private final Receiver<Rating> receiver;

   public FetchCreditRatingCommand(String cpr, Receiver<Rating> receiver) {
      this.cpr = cpr;
      this.receiver = receiver;
   }

   @Override
   public void execute() {
      receiver.receive(CreditRator.i().rate(cpr));
   }
}
