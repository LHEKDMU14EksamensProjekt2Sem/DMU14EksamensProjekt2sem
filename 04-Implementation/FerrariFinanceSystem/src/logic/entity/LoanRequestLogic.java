package logic.entity;

import domain.LoanRequest;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;

public interface LoanRequestLogic {
   void createLoanRequest(LoanRequest loanRequest, ConnectionHandler con) throws SQLException;

   void createLoanRequest(LoanRequest loanRequest) throws SQLException;

   List<LoanRequest> listLoanRequests() throws SQLException;

   void approveLoanRequest(LoanRequest loanRequest) throws SQLException;

   void declineLoanRequest(LoanRequest loanRequest) throws SQLException;
}
