package data.access;

import domain.LoanRequest;
import domain.LoanRequestStatus;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class LoanRequestAccess {
   private ConnectionHandler con;

   public LoanRequestAccess(ConnectionHandler con) {
      this.con = con;
   }

   public void createLoanRequest(LoanRequest loanRequest) throws SQLException {
      createLoanRequests(Arrays.asList(loanRequest));
   }

   public void createLoanRequests(List<LoanRequest> loanRequests) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
         for (LoanRequest loanRequest : loanRequests) {
            st.setInt(1, loanRequest.getSale().getId());
            st.setString(2, loanRequest.getStatus().toString());
            st.setInt(3, loanRequest.getStatusByEmployee().getId());
            st.setDate(4, loanRequest.getDate());
            st.setBigDecimal(5, loanRequest.getLoanAmount().asBigDecimal());
            st.setBigDecimal(6,
                    loanRequest.hasPreferredRepayment()
                            ? loanRequest.getPreferredRepayment().asBigDecimal()
                            : null);
            st.setInt(7, loanRequest.getPreferredTerm());
            st.executeUpdate();
         }
      }
   }

   // TODO
   public LoanRequest readLoanRequest(int id) throws SQLException {
      return null;
   }

   // TODO
   public List<LoanRequest> listLoanRequests(LoanRequestStatus status) throws SQLException {
      return null;
   }

   // TODO
   public void updateLoanRequest(LoanRequest loanRequest) throws SQLException {
      return;
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO loan_request"
              + "(id, status_id, status_by_employee_id, date, loan_amount, pref_repayment, pref_term)"
              + "VALUES (?, (SELECT id FROM loan_request_status WHERE status = ?), ?, ?, ?, ?, ?)";

      // TODO
      static final String SELECT_ONE = "";

      // TODO
      static final String SELECT_MANY = "";

      // TODO
      static final String UPDATE_ONE = "";
   }
}
