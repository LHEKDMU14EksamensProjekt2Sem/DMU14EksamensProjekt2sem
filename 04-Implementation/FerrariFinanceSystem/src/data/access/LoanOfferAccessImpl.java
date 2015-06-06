package data.access;

import domain.LoanOffer;
import util.finance.Money;
import util.jdbc.ConnectionHandler;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoanOfferAccessImpl implements LoanOfferAccess {
   private final ConnectionHandler con;

   public LoanOfferAccessImpl(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void createLoanOffers(List<LoanOffer> loanOffers) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(
              SQL.INSERT_ONE, Statement.RETURN_GENERATED_KEYS)) {
         for (LoanOffer offer : loanOffers) {
            st.setDate(1, Date.valueOf(offer.getDate()));
            st.setBigDecimal(2, offer.getPrincipal().asBigDecimal());
            st.setDouble(3, offer.getInterestRate());
            st.executeUpdate();

            try (ResultSet rs = st.getGeneratedKeys()) {
               rs.next();
               offer.setId(rs.getInt(1));
            }
         }
      }
   }

   @Override
   public void createLoanOffer(LoanOffer loanOffer) throws SQLException {
      createLoanOffers(Collections.singletonList(loanOffer));
   }

   @Override
   public List<LoanOffer> listLoanOffers() throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.SELECT_ALL);
           ResultSet rs = st.executeQuery()) {
         List<LoanOffer> res = new ArrayList<>();
         while (rs.next()) {
            LoanOffer offer = new LoanOffer();
            offer.setId(rs.getInt("id"));
            offer.setDate(rs.getDate("date").toLocalDate());
            Money m = new Money(rs.getBigDecimal("principal"));
            offer.setPrincipal(m);
            offer.setInterestRate(rs.getDouble("interest_rate"));
            res.add(offer);
         }
         return res;
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO loan_offer"
              + " (date, principal, interest_rate)"
              + " VALUES (?, ?, ?)";

      static final String SELECT_ALL
              = "SELECT id, date, principal, interest_rate"
              + " FROM loan_offer";
   }
}
