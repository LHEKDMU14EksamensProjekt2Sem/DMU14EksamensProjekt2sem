package dev.command;

import domain.Employee;
import domain.Identity;
import logic.service.EmployeeService;
import logic.service.EmployeeServiceImpl;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import static dev.sample.EmployeeSample.*;

public class CreateEmployeeSampleCommand implements Callable<List<Employee>> {
   private final ConnectionHandler con;

   public CreateEmployeeSampleCommand(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public List<Employee> call() throws SQLException {
      return createEmployees();
   }

   private List<Employee> createEmployees() throws SQLException {
      List<Employee> employees = newEmployees();
      List<String> cprs = newEmployeeCPRs();
      EmployeeService logic = new EmployeeServiceImpl();
      for (int i = 0; i < cprs.size(); i++) {
         Employee em = employees.get(i);
         Identity identity = new Identity();
         identity.setCPR(cprs.get(i));
         identity.setPerson(em.getPerson());
         logic.createEmployee(em, identity, con);
      }
      return employees;
   }
}
