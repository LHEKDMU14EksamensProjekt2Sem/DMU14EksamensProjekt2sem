package data.access;

import domain.LoanOffer;
import domain.RepaymentPlanPayment;

import java.sql.SQLException;
import java.util.List;

public interface LoanOfferPaymentAccess {
   void createPayments(LoanOffer offer) throws SQLException;

   List<RepaymentPlanPayment> listPayments(LoanOffer offer) throws SQLException;
}
