package logic.service;

import domain.Identity;
import domain.LoanOffer;
import domain.LoanRequest;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface LoanRequestService {
   void createLoanRequest(LoanRequest loanRequest, ConnectionHandler con) throws SQLException;

   void createLoanRequest(LoanRequest loanRequest) throws SQLException;

   List<LoanRequest> listLoanRequests() throws SQLException;

   void approveLoanRequest(LoanRequest loanRequest) throws SQLException;

   void declineLoanRequest(LoanRequest loanRequest) throws SQLException;

   Optional<LoanOffer> submitLoanRequest(LoanRequest loanRequest,
                                         Optional<Identity> identity) throws SQLException;
}
