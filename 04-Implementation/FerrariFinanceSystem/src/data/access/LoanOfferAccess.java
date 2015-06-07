package data.access;

import domain.LoanOffer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface LoanOfferAccess {
   void createLoanOffers(List<LoanOffer> loanOffers) throws SQLException;

   void createLoanOffer(LoanOffer loanOffer) throws SQLException;

   Optional<LoanOffer> readLoanOffer(int id) throws SQLException;

   List<LoanOffer> listLoanOffers() throws SQLException;
}
