package data.access;

import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeRoleAccess {
   private ConnectionHandler con;

   public EmployeeRoleAccess(ConnectionHandler con) {
      this.con = con;
   }

   public void createEmployeeRole(String role) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
         st.setString(1, role);
         st.executeUpdate();
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO employee_role(role) VALUES (?)";
   }
}
