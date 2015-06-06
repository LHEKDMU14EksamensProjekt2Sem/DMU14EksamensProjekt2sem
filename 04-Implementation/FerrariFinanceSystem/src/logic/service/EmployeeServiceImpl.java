package logic.service;

import data.ConnectionService;
import data.access.EmployeeAccessImpl;
import domain.Employee;
import domain.Identity;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {
   @Override
   public void createEmployee(Employee employee, Identity identity,
                              ConnectionHandler con) throws SQLException {
      new PersonServiceImpl().createPerson(identity, con);
      new EmployeeAccessImpl(con).createEmployee(employee);
   }

   @Override
   public Optional<Employee> readEmployee(int id) throws SQLException {
      return ConnectionService.query(con ->
              new EmployeeAccessImpl(con).readEmployee(id));
   }
}
