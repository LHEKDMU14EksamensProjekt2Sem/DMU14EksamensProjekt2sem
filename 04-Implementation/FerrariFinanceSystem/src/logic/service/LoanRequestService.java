package logic.service;

import domain.Identity;
import domain.LoanOffer;
import domain.LoanRequest;
import domain.LoanRequestStatus;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface LoanRequestService {
   void createLoanRequests(List<LoanRequest> loanRequests, ConnectionHandler con) throws SQLException;

   void createLoanRequest(LoanRequest loanRequest) throws SQLException;

   Optional<LoanRequest> readLoanRequest(int id) throws SQLException;

   List<LoanRequest> listLoanRequests(LoanRequestStatus status) throws SQLException;

   void updateLoanRequestStatus(LoanRequest loanRequest) throws SQLException;

   Optional<LoanOffer> submitLoanRequest(LoanRequest loanRequest,
                                         Optional<Identity> identity) throws SQLException;
}
