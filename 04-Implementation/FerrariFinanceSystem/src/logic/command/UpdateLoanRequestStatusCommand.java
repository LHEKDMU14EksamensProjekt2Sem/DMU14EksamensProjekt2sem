package logic.command;

import domain.LoanRequest;
import logic.service.LoanRequestServiceImpl;

import java.util.concurrent.Callable;

public class UpdateLoanRequestStatusCommand implements Callable<Void> {
   private final LoanRequest loanRequest;

   public UpdateLoanRequestStatusCommand(LoanRequest loanRequest) {
      this.loanRequest = loanRequest;
   }

   @Override
   public Void call() throws Exception {
      new LoanRequestServiceImpl().updateLoanRequestStatus(loanRequest);
      return null;
   }
}
