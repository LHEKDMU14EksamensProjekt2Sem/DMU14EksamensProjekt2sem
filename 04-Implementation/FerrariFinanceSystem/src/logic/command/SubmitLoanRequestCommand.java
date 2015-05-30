package logic.command;

import domain.Identity;
import domain.LoanOffer;
import domain.LoanRequest;
import logic.service.LoanRequestServiceImpl;

import java.util.Optional;
import java.util.concurrent.Callable;

public class SubmitLoanRequestCommand implements Callable<Optional<LoanOffer>> {
   private final LoanRequest loanRequest;
   private final Identity identity;

   public SubmitLoanRequestCommand(LoanRequest loanRequest, Identity identity) {
      this.loanRequest = loanRequest;
      this.identity = identity;
   }

   @Override
   public Optional<LoanOffer> call() throws Exception {
      return new LoanRequestServiceImpl()
              .submitLoanRequest(loanRequest, Optional.ofNullable(identity));
   }
}
