package logic.service;

import domain.LoanOffer;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface LoanOfferService {
   void createLoanOffers(List<LoanOffer> loanOffers, ConnectionHandler con) throws SQLException;

   void createLoanOffer(LoanOffer loanOffer) throws SQLException;

   Optional<LoanOffer> readLoanOffer(int id) throws SQLException;

   List<LoanOffer> listLoanOffers() throws SQLException;
}
