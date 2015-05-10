package query;

import com.ferrari.finances.dk.bank.InterestRate;
import domain.LoanRequest;

public class InterestRateQuery extends AbstractQuery {
   private final InterestRate rate;
   private final LoanRequest loanRequest;

   public InterestRateQuery(InterestRate rate, LoanRequest loanRequest, long timeout) {
      super(timeout);
      this.rate = rate;
      this.loanRequest = loanRequest;
   }

   @Override
   public void run() {
      double r = rate.todaysRate();
      loanRequest.setInterestRate(r);
   }
}
