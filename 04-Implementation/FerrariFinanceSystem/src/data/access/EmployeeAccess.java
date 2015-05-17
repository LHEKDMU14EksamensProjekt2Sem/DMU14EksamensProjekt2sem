package data.access;

import domain.Employee;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeAccess {
   private ConnectionHandler con;

   public EmployeeAccess(ConnectionHandler con) {
      this.con = con;
   }

   public void createEmployee(Employee employee) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
         st.setInt(1, employee.getPerson().getId());
         st.setString(2, employee.getRole().toString());
         st.executeUpdate();
      }
   }

   // TODO
   public Employee readEmployee(int id) throws SQLException {
      return null;
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO employee(id, role_id)"
              + "VALUES (?, (SELECT id FROM employee_role WHERE role = ?))";

      // TODO
      static final String SELECT_ONE = "";
   }
}
