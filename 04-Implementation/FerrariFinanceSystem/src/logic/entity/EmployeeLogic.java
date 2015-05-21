package logic.entity;

import domain.Employee;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;

public interface EmployeeLogic {
   void createEmployee(Employee employee, String cpr, ConnectionHandler con) throws SQLException;

   void createEmployee(Employee employee, String cpr) throws SQLException;

   Employee readEmployee(int id) throws SQLException;
}
