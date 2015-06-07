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
import java.util.Optional;

public class LoanOfferAccessImpl implements LoanOfferAccess {
   private final ConnectionHandler con;

   public LoanOfferAccessImpl(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void createLoanOffers(List<LoanOffer> loanOffers) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(
              SQL.INSERT_ONE, Statement.RETURN_GENERATED_KEYS)) {
         for (LoanOffer lo : loanOffers) {
            st.setInt(1, lo.getId());
            st.setDate(2, Date.valueOf(lo.getDate()));
            st.setBigDecimal(3, lo.getPrincipal().asBigDecimal());
            st.setDouble(4, lo.getInterestRate());
            st.executeUpdate();

            try (ResultSet rs = st.getGeneratedKeys()) {
               rs.next();
               lo.setId(rs.getInt(1));
            }
         }
      }
   }

   @Override
   public void createLoanOffer(LoanOffer loanOffer) throws SQLException {
      createLoanOffers(Collections.singletonList(loanOffer));
   }

   @Override
   public Optional<LoanOffer> readLoanOffer(int id) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.SELECT_ONE)) {
         st.setInt(1, id);

         try (ResultSet rs = st.executeQuery()) {
            if (rs.next())
               return Optional.of(extractLoanOffer(rs));
            else
               return Optional.empty();
         }
      }
   }

   @Override
   public List<LoanOffer> listLoanOffers() throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.SELECT_ALL);
           ResultSet rs = st.executeQuery()) {
         List<LoanOffer> res = new ArrayList<>();
         while (rs.next())
            res.add(extractLoanOffer(rs));

         return res;
      }
   }

   private LoanOffer extractLoanOffer(ResultSet rs) throws SQLException {
      LoanOffer lo = new LoanOffer();
      lo.setId(rs.getInt("id"));
      lo.setDate(rs.getDate("date").toLocalDate());
      Money m = new Money(rs.getBigDecimal("principal"));
      lo.setPrincipal(m);
      lo.setInterestRate(rs.getDouble("interest_rate"));
      return lo;
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO loan_offer"
              + " (id, \"date\", principal, interest_rate)"
              + " VALUES (?, ?, ?, ?)";

      static final String SELECT_BASE
              = "SELECT id, \"date\", principal, interest_rate"
              + " FROM loan_offer";

      static final String SELECT_ALL
              = SELECT_BASE
              + " ORDER BY \"date\" DESC";

      static final String SELECT_ONE
              = SELECT_BASE
              + " WHERE id = ?";
   }
}
