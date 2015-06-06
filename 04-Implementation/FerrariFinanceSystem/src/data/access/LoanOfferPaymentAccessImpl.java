package data.access;

import domain.LoanOffer;
import domain.RepaymentPlanPayment;
import util.finance.Money;
import util.jdbc.ConnectionHandler;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanOfferPaymentAccessImpl implements LoanOfferPaymentAccess {
   private final ConnectionHandler con;

   public LoanOfferPaymentAccessImpl(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void createPayments(LoanOffer offer) throws SQLException {
      deletePayments(offer);

      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
         for (RepaymentPlanPayment p : offer.getPayments()) {
            st.setInt(1, offer.getId());
            st.setDate(2, Date.valueOf(p.getDate()));
            st.setBigDecimal(3, p.getBalance().asBigDecimal());
            st.setBigDecimal(4, p.getAmount().asBigDecimal());
            st.setBigDecimal(5, p.getRepayment().asBigDecimal());
            st.setBigDecimal(6, p.getInterest().asBigDecimal());
            st.executeUpdate();
         }
      }
   }

   @Override
   public List<RepaymentPlanPayment> listPayments(LoanOffer offer) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.SELECT_MANY)) {
         st.setInt(1, offer.getId());

         try (ResultSet rs = st.executeQuery()) {
            List<RepaymentPlanPayment> payments = new ArrayList<>();
            while (rs.next()) {
               RepaymentPlanPayment p = new RepaymentPlanPayment();
               p.setDate(rs.getDate("date").toLocalDate());
               Money m = new Money(rs.getBigDecimal("balance"));
               p.setBalance(m);
               m = new Money(rs.getBigDecimal("amount"));
               p.setAmount(m);
               m = new Money(rs.getBigDecimal("repayment"));
               p.setRepayment(m);
               m = new Money(rs.getBigDecimal("interest"));
               p.setInterest(m);
               payments.add(p);
            }
            return payments;
         }
      }
   }

   private void deletePayments(LoanOffer offer) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.DELETE_MANY)) {
         st.setInt(1, offer.getId());
         st.executeUpdate();
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO loan_offer_payment"
              + " (offer_id, \"date\", balance, amount, repayment, interest)"
              + " VALUES (?, ?, ?, ?, ?, ?)";

      static final String SELECT_MANY
              = "SELECT \"date\", balance, amount, repayment, interest"
              + " FROM loan_offer_payment"
              + " WHERE offer_id = ?";

      static final String DELETE_MANY
              = "DELETE FROM loan_offer_payment"
              + " WHERE offer_id = ?";
   }
}
