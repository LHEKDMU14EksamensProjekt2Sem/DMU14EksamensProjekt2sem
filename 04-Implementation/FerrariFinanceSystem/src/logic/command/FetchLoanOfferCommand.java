package logic.command;

import domain.LoanOffer;
import logic.service.LoanOfferServiceImpl;

import java.util.Optional;
import java.util.concurrent.Callable;

public class FetchLoanOfferCommand implements Callable<Optional<LoanOffer>> {
   private final int id;

   public FetchLoanOfferCommand(int id) {
      this.id = id;
   }

   @Override
   public Optional<LoanOffer> call() throws Exception {
      return new LoanOfferServiceImpl().readLoanOffer(id);
   }
}
