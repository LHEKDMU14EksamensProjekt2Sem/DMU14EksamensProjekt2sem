package data.access;

import domain.LoanRequest;
import domain.LoanRequestStatus;
import util.jdbc.ConnectionHandler;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collections;
import java.util.List;

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

   // TODO
   @Override
   public LoanRequest readLoanRequest(int id) throws SQLException {
      return null;
   }

   // TODO
   @Override
   public List<LoanRequest> listLoanRequests(LoanRequestStatus status) throws SQLException {
      return null;
   }

   // TODO
   @Override
   public void updateLoanRequestStatus(LoanRequest loanRequest) throws SQLException {
      return;
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO loan_request"
              + "(id, status_id, status_by_employee_id, date, loan_amount, pref_payment, pref_term)"
              + "VALUES (?, (SELECT id FROM loan_request_status WHERE status = ?), ?, ?, ?, ?, ?)";

      // TODO
      static final String SELECT_ONE = "";

      // TODO
      static final String SELECT_MANY = "";

      // TODO
      static final String UPDATE_ONE = "";
   }
}
