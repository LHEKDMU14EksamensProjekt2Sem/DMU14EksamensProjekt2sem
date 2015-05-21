package data.access;

import domain.LoanRequestStatus;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class LoanRequestStatusAccessImpl implements LoanRequestStatusAccess {
   private ConnectionHandler con;

   public LoanRequestStatusAccessImpl(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void createLoanRequestStatuses(List<LoanRequestStatus> statuses) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
         for (LoanRequestStatus status : statuses) {
            st.setString(1, status.name());
            st.executeUpdate();
         }
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO loan_request_status(status) VALUES (?)";
   }
}
