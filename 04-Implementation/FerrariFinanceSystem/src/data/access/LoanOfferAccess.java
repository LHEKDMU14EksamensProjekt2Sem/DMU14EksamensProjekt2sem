package data.access;

import domain.LoanOffer;

import java.sql.SQLException;
import java.util.List;

public interface LoanOfferAccess {
   void createLoanOffers(List<LoanOffer> loanOffers) throws SQLException;

   void createLoanOffer(LoanOffer loanOffer) throws SQLException;

   List<LoanOffer> listLoanOffers() throws SQLException;
}
