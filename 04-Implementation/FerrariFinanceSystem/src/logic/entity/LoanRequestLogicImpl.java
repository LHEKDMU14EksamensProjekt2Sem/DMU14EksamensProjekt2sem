package logic.entity;

import data.ConnectionService;
import data.access.LoanRequestAccess;
import data.access.SaleAccess;
import domain.LoanRequest;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;

public class LoanRequestLogicImpl implements LoanRequestLogic {
   @Override
   public void createLoanRequest(LoanRequest loanRequest, ConnectionHandler con) throws SQLException {
      new SaleAccess(con).createSale(loanRequest.getSale());
      new LoanRequestAccess(con).createLoanRequest(loanRequest);
   }

   @Override
   public void createLoanRequest(LoanRequest loanRequest) throws SQLException {
      ConnectionService.execute(con ->
              createLoanRequest(loanRequest));
   }

   @Override
   public List<LoanRequest> listLoanRequests() throws SQLException {
      return null;
   }

   @Override
   public void approveLoanRequest(LoanRequest loanRequest) throws SQLException {

   }

   @Override
   public void declineLoanRequest(LoanRequest loanRequest) throws SQLException {

   }
}
