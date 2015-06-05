package logic.command;

import domain.LoanRequest;
import logic.service.LoanRequestServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

public class FetchLoanRequestsCommand implements Callable<List<LoanRequest>> {
   @Override
   public List<LoanRequest> call() throws SQLException {
      return new LoanRequestServiceImpl().listLoanRequests();
   }
}
