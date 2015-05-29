package data.access;

import domain.LoanOffer;
import domain.LoanOfferPayment;

import java.sql.SQLException;
import java.util.List;

public interface LoanOfferPaymentAccess {
   void createPayments(List<LoanOfferPayment> payments) throws SQLException;

   List<LoanOfferPayment> listPayments(LoanOffer offer) throws SQLException;
}
