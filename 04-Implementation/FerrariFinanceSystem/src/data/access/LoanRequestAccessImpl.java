package data.access;

import domain.LoanRequest;
import domain.LoanRequestStatus;
import domain.Sale;
import util.finance.Money;
import util.jdbc.ConnectionHandler;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LoanRequestAccessImpl implements LoanRequestAccess {
   private ConnectionHandler con;

   public LoanRequestAccessImpl(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void createLoanRequest(LoanRequest loanRequest) throws SQLException {
      createLoanRequests(Collections.singletonList(loanRequest));
   }

   @Override
   public void createLoanRequests(List<LoanRequest> loanRequests) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
         for (LoanRequest loanRequest : loanRequests) {
            st.setInt(1, loanRequest.getSale().getId());
            st.setString(2, loanRequest.getStatus().toString());
            st.setInt(3, loanRequest.getStatusByEmployee().getId());
            st.setDate(4, Date.valueOf(loanRequest.getDate()));
            st.setBigDecimal(5, loanRequest.getLoanAmount().asBigDecimal());

            if (loanRequest.hasPreferredPayment())
               st.setBigDecimal(6, loanRequest.getPreferredPayment().asBigDecimal());
            else
               st.setNull(6, Types.NUMERIC);

            if (loanRequest.hasPreferredTerm())
               st.setInt(7, loanRequest.getPreferredTerm());
            else
               st.setNull(7, Types.INTEGER);

            st.executeUpdate();
         }
      }
   }

   @Override
   public Optional<LoanRequest> readLoanRequest(int id) throws SQLException {
      // TODO
      return Optional.empty();
   }

   @Override
   public List<LoanRequest> listLoanRequests() throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.SELECT_ALL)) {
         try (ResultSet rs = st.executeQuery()) {
            List<LoanRequest> res = new ArrayList<>();

            while (rs.next()) {
               LoanRequest lr = new LoanRequest();
               lr.setDate(rs.getDate("date").toLocalDate());
               Money m = new Money(rs.getBigDecimal("loan_amount"));
               lr.setLoanAmount(m);

               BigDecimal bd = rs.getBigDecimal("pref_payment");
               if (bd != null)
                  lr.setPreferredPayment(new Money(bd));

               lr.setPreferredTerm(rs.getInt("pref_term"));
               lr.setStatus(LoanRequestStatus.valueOf(rs.getString("status")));

               Sale s = new Sale();
               s.setId(rs.getInt("id"));
               m = new Money(rs.getBigDecimal("base_price"));
               s.setBasePrice(m);
               m = new Money(rs.getBigDecimal("selling_price"));
               s.setSellingPrice(m);
               lr.setSale(s);

               res.add(lr);
            }

            return res;
         }
      }
   }

   @Override
   public void updateLoanRequestStatus(LoanRequest loanRequest) throws SQLException {
      // TODO
      return;
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO loan_request"
              + "(id, status_id, status_by_employee_id, date, loan_amount, pref_payment, pref_term)"
              + "VALUES (?, (SELECT id FROM loan_request_status WHERE status = ?), ?, ?, ?, ?, ?)";

      static final String SELECT_ONE
              = "SELECT lr.id, date, loan_amount, pref_payment, pref_term"
              + " status, base_price, selling_price"
              + " FROM loan_request lr"
              + " LEFT JOIN loan_request_status lrs ON lrs.id = status_id"
              + " LEFT JOIN sale s ON s.id = lr.id"
              + " WHERE lr.id = ?";

      static final String SELECT_ALL
              = "SELECT lr.id, date, loan_amount, pref_payment, pref_term,"
              + " status, base_price, selling_price"
              + " FROM loan_request lr"
              + " LEFT JOIN loan_request_status lrs ON lrs.id = status_id"
              + " LEFT JOIN sale s ON s.id = lr.id";

      // TODO
      static final String UPDATE_ONE = "";
   }
}
