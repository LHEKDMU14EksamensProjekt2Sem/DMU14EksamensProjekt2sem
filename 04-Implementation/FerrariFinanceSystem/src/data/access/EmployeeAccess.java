package data.access;

import domain.Employee;
import domain.LoanRequest;
import domain.Sale;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface EmployeeAccess {
   void createEmployee(Employee employee) throws SQLException;

   void createEmployees(List<Employee> employees) throws SQLException;

   Optional<Employee> readEmployee(int id) throws SQLException;

   Employee readEmployee(LoanRequest loanRequest) throws SQLException;

   Employee readEmployee(Sale sale) throws SQLException;
}
