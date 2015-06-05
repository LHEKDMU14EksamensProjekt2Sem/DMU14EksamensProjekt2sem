package data.access;

import domain.LoanRequest;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface LoanRequestAccess {
   void createLoanRequest(LoanRequest loanRequest) throws SQLException;

   void createLoanRequests(List<LoanRequest> loanRequests) throws SQLException;

   Optional<LoanRequest> readLoanRequest(int id) throws SQLException;

   List<LoanRequest> listLoanRequests() throws SQLException;

   void updateLoanRequestStatus(LoanRequest loanRequest) throws SQLException;
}
