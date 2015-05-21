package logic.entity;

import domain.LoanRequest;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;

public interface LoanRequestLogic {
   void createLoanRequest(LoanRequest loanRequest, ConnectionHandler con) throws SQLException;

   void createLoanRequest(LoanRequest loanRequest) throws SQLException;
}
