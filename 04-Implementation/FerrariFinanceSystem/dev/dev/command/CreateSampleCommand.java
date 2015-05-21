package dev.command;

import dev.util.SampleUtil;
import domain.Customer;
import domain.Employee;
import domain.Identity;
import logic.entity.CarModelLogic;
import logic.entity.CarModelLogicImpl;
import logic.entity.CustomerLogic;
import logic.entity.CustomerLogicImpl;
import logic.entity.EmployeeLogic;
import logic.entity.EmployeeLogicImpl;
import util.command.Command;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;

public class CreateSampleCommand implements Command {
   private final ConnectionHandler con;

   public CreateSampleCommand(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void execute() throws SQLException {
      createEmployees();
      createCustomers();
      createCarModel();
   }

   private void createEmployees() throws SQLException {
      List<Employee> employees = SampleUtil.newEmployees();
      List<String> cprs = SampleUtil.newEmployeeCPRs();
      EmployeeLogic logic = new EmployeeLogicImpl();
      for (int i = 0; i < cprs.size(); i++) {
         Employee em = employees.get(i);
         Identity identity = new Identity();
         identity.setCPR(cprs.get(i));
         identity.setPerson(em.getPerson());
         logic.createEmployee(em, identity, con);
      }
   }

   private void createCustomers() throws SQLException {
      List<Customer> customers = SampleUtil.newCustomers();
      List<String> cprs = SampleUtil.newCustomerCPRs();
      CustomerLogic logic = new CustomerLogicImpl();
      for (int i = 0; i < cprs.size(); i++) {
         Customer c = customers.get(i);
         Identity identity = new Identity();
         identity.setCPR(cprs.get(i));
         identity.setPerson(c.getPerson());
         logic.createCustomer(c, identity, con);
      }
   }

   private void createCarModel() throws SQLException {
      CarModelLogic logic = new CarModelLogicImpl();
      logic.createCarModels(SampleUtil.newCarModels(), con);
   }
}
