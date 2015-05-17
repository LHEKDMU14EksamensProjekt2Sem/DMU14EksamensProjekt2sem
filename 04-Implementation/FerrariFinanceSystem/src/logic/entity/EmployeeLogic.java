package logic.entity;

import data.ConnectionHandlerFactory;
import data.access.EmployeeAccess;
import data.access.PersonAccess;
import domain.Employee;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;

public class EmployeeLogic {
   public void createEmployee(Employee employee, ConnectionHandler con) throws SQLException {
      new PersonAccess(con).createPerson(employee.getPerson());
      new EmployeeAccess(con).createEmployee(employee);
   }

   public void createEmployee(Employee employee) throws SQLException {
      try (ConnectionHandler con = ConnectionHandlerFactory.create()) {
         try {
            createEmployee(employee, con);
            con.commit();
         } catch (SQLException e) {
            con.rollback();
            throw e;
         }
      }
   }
}
