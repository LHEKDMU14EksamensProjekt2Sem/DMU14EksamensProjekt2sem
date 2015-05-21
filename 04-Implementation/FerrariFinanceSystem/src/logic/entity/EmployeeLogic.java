package logic.entity;

import domain.Employee;
import domain.Identity;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;

public interface EmployeeLogic {
   void createEmployee(Employee employee, Identity identity, ConnectionHandler con) throws SQLException;

   Employee readEmployee(int id) throws SQLException;
}
