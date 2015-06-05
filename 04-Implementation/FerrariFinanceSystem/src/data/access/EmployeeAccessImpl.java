package data.access;

import domain.Employee;
import domain.EmployeeRole;
import domain.LoanRequest;
import domain.Person;
import domain.PostalCode;
import domain.Sale;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EmployeeAccessImpl implements EmployeeAccess {
   private ConnectionHandler con;

   public EmployeeAccessImpl(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void createEmployee(Employee employee) throws SQLException {
      createEmployees(Collections.singletonList(employee));
   }

   @Override
   public void createEmployees(List<Employee> employees) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
         for (Employee employee : employees) {
            st.setInt(1, employee.getPerson().getId());
            st.setString(2, employee.getRole().toString());
            st.executeUpdate();
         }
      }
   }

   @Override
   public Optional<Employee> readEmployee(int id) throws SQLException {
      return readEmployee(id, SQL.BY_ID);
   }

   @Override
   public Employee readEmployee(LoanRequest loanRequest) throws SQLException {
      return readEmployee(loanRequest.getId(), SQL.BY_LOAN_REQUEST).get();
   }

   @Override
   public Employee readEmployee(Sale sale) throws SQLException {
      return readEmployee(sale.getId(), SQL.BY_SALE).get();
   }

   private Optional<Employee> readEmployee(int id, String by) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.selectOne(by))) {
         st.setInt(1, id);

         try (ResultSet rs = st.executeQuery()) {
            Employee employee = new Employee();
            Person person = new Person();
            PostalCode postalCode = new PostalCode();

            person.setPostalCode(postalCode);
            employee.setPerson(person);

            if (rs.next()) {
               person.setId(rs.getInt("id"));
               person.setFirstName(rs.getString("first_name"));
               person.setLastName(rs.getString("last_name"));
               person.setStreet(rs.getString("street"));
               postalCode.setPostalCode(rs.getShort("postal_code"));
               postalCode.setCity(rs.getString("city"));
               person.setPhone(rs.getInt("phone"));
               person.setEmail(rs.getString("email"));
               employee.setRole(EmployeeRole.valueOf(rs.getString("role")));

               return Optional.of(employee);
            } else {
               return Optional.empty();
            }
         }
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO employee(id, role_id)"
              + " VALUES (?, (SELECT id FROM employee_role WHERE role = ?))";

      static final String SELECT_ONE
              = "SELECT e.id, first_name, last_name, street, p.postal_code, city, phone, email, role"
              + " FROM employee e"
              + " JOIN person p ON p.id = e.id"
              + " JOIN postal_code pc ON pc.postal_code = p.postal_code"
              + " JOIN employee_role er ON e.role_id = er.id"
              + " WHERE e.id = %s";

      static final String BY_ID = "?";

      static final String BY_SALE
              = "(SELECT seller_id FROM sale WHERE id = ?)";

      static final String BY_LOAN_REQUEST
              = "(SELECT status_by_employee_id"
              + " FROM loan_request WHERE id = ?)";

      static String selectOne(String by) {
         return String.format(SELECT_ONE, by);
      }
   }
}
