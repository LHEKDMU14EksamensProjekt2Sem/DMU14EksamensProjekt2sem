package logic.command;

import domain.LoanRequest;
import domain.LoanRequestStatus;
import logic.service.LoanRequestServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

public class FetchLoanRequestsCommand implements Callable<List<LoanRequest>> {
   private final LoanRequestStatus status;

   public FetchLoanRequestsCommand(LoanRequestStatus status) {
      this.status = status;
   }

   @Override
   public List<LoanRequest> call() throws SQLException {
      return new LoanRequestServiceImpl().listLoanRequests(status);
   }
}
