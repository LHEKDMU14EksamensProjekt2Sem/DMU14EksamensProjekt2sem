package data.access;

import domain.LoanRequest;
import domain.LoanRequestStatus;

import java.sql.SQLException;
import java.util.List;

public interface LoanRequestAccess {
   void createLoanRequest(LoanRequest loanRequest) throws SQLException;

   void createLoanRequests(List<LoanRequest> loanRequests) throws SQLException;

   LoanRequest readLoanRequest(int id) throws SQLException;

   List<LoanRequest> listLoanRequests(LoanRequestStatus status) throws SQLException;

   void updateLoanRequestStatus(LoanRequest loanRequest) throws SQLException;
}
