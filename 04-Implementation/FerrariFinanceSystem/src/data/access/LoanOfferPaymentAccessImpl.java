package data.access;

import domain.LoanOffer;
import domain.LoanOfferPayment;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LoanOfferPaymentAccessImpl implements LoanOfferPaymentAccess {
   private final ConnectionHandler con;

   public LoanOfferPaymentAccessImpl(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void createPayments(List<LoanOfferPayment> payments) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
         for (LoanOfferPayment p : payments) {
            st.setDate(1, p.getDate());
            st.setBigDecimal(2, p.getBalance().asBigDecimal());
            st.setBigDecimal(3, p.getAmount().asBigDecimal());
            st.setBigDecimal(4, p.getRepayment().asBigDecimal());
            st.setBigDecimal(5, p.getInterest().asBigDecimal());
            st.executeUpdate();
         }
      }
   }

   @Override
   public List<LoanOfferPayment> listPayments(LoanOffer offer) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.SELECT_MANY)) {
         st.setInt(1, offer.getId());
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO loan_offer_payment"
              + " (date, balance, amount, repayment, interest)"
              + " VALUES (?, ?, ?, ?, ?)";

      static final String SELECT_MANY
              = "SELECT date, balance, amount, repayment, interest"
              + " FROM loan_offer_payment"
              + " WHERE offer_id = ?";
   }
}
