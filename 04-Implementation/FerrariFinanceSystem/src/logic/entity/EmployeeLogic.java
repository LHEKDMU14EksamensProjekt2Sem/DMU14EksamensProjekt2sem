package logic.entity;

import data.ConnectionHandlerFactory;
import data.access.EmployeeAccess;
import domain.Employee;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;

public class EmployeeLogic {
   public void createEmployee(Employee employee, String cpr, ConnectionHandler con) throws SQLException {
      new PersonLogic().createPerson(employee.getPerson(), cpr, con);
      new EmployeeAccess(con).createEmployee(employee);
   }

   public void createEmployee(Employee employee, String cpr) throws SQLException {
      try (ConnectionHandler con = ConnectionHandlerFactory.create()) {
         try {
            createEmployee(employee, cpr, con);
            con.commit();
         } catch (SQLException e) {
            con.rollback();
            throw e;
         }
      }
   }
}
