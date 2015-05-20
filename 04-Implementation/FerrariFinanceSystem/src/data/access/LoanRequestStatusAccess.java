package data.access;

import domain.LoanRequestStatus;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoanRequestStatusAccess {
   private ConnectionHandler con;

   public LoanRequestStatusAccess(ConnectionHandler con) {
      this.con = con;
   }

   public void createLoanRequestStatus(LoanRequestStatus status) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
         st.setString(1, status.toString());
         st.executeUpdate();
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO loan_request_status(status) VALUES (?)";
   }
}
