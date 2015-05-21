package data.access;

import domain.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeAccess {
   void createEmployee(Employee employee) throws SQLException;

   void createEmployees(List<Employee> employees) throws SQLException;

   Employee readEmployee(int id) throws SQLException;
}
