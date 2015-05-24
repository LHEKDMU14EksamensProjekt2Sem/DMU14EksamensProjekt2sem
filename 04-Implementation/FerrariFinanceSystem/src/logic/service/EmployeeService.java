package logic.service;

import domain.Employee;
import domain.Identity;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;

public interface EmployeeService {
   void createEmployee(Employee employee, Identity identity, ConnectionHandler con) throws SQLException;

   Employee readEmployee(int id) throws SQLException;
}
