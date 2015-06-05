package logic.service;

import domain.Employee;
import domain.Identity;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.Optional;

public interface EmployeeService {
   void createEmployee(Employee employee, Identity identity, ConnectionHandler con) throws SQLException;

   Optional<Employee> readEmployee(int id) throws SQLException;
}
