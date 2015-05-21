package logic.entity;

import data.ConnectionHandlerFactory;
import data.access.LoanRequestAccess;
import data.access.SaleAccess;
import domain.LoanRequest;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;

public class LoanRequestLogicImpl implements LoanRequestLogic {
   @Override
   public void createLoanRequest(LoanRequest loanRequest, ConnectionHandler con) throws SQLException {
      new SaleAccess(con).createSale(loanRequest.getSale());
      new LoanRequestAccess(con).createLoanRequest(loanRequest);
   }

   @Override
   public void createLoanRequest(LoanRequest loanRequest) throws SQLException {
      try (ConnectionHandler con = ConnectionHandlerFactory.create()) {
         try {
            createLoanRequest(loanRequest);
            con.commit();
         } catch (SQLException e) {
            con.rollback();
            throw e;
         }
      }
   }
}
