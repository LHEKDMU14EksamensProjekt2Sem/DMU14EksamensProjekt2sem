package logic.command;

import domain.LoanRequest;
import logic.service.LoanRequestServiceImpl;

import java.util.Optional;
import java.util.concurrent.Callable;

public class FetchLoanRequestCommand implements Callable<Optional<LoanRequest>> {
   private final int id;

   public FetchLoanRequestCommand(int id) {
      this.id = id;
   }

   @Override
   public Optional<LoanRequest> call() throws Exception {
      return new LoanRequestServiceImpl().readLoanRequest(id);
   }
}
