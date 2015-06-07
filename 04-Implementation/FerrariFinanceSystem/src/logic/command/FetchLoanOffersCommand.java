package logic.command;

import domain.LoanOffer;
import logic.service.LoanOfferServiceImpl;

import java.util.List;
import java.util.concurrent.Callable;

public class FetchLoanOffersCommand implements Callable<List<LoanOffer>> {
   @Override
   public List<LoanOffer> call() throws Exception {
      return new LoanOfferServiceImpl().listLoanOffers();
   }
}
