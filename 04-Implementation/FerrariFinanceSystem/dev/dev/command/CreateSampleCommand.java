package dev.command;

import domain.CarModel;
import domain.Customer;
import domain.Employee;
import logic.entity.CarModelLogic;
import logic.entity.CarModelLogicImpl;
import logic.entity.CustomerLogic;
import logic.entity.CustomerLogicImpl;
import logic.entity.EmployeeLogic;
import logic.entity.EmployeeLogicImpl;
import dev.util.SampleUtil;
import util.command.Command;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;

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
      Employee[] employees = SampleUtil.newEmployees();
      String[] cprs = SampleUtil.newEmployeeCPRs();
      EmployeeLogic logic = new EmployeeLogicImpl();
      for (int i = 0; i < cprs.length; i++) {
         logic.createEmployee(employees[i], cprs[i], con);
      }
   }

   private void createCustomers() throws SQLException {
      Customer[] customers = SampleUtil.newCustomers();
      String[] cprs = SampleUtil.newEmployeeCPRs();
      CustomerLogic logic = new CustomerLogicImpl();
      for (int i = 0; i < cprs.length; i++) {
         logic.createCustomer(customers[i], cprs[i], con);
      }
   }
   
   private void createCarModel() throws SQLException {
      CarModel[] carModels = SampleUtil.newCarModels();
      CarModelLogic logic = new CarModelLogicImpl();
      for (CarModel carModel : carModels) {
         logic.createCarModel(carModel, con);
      }
      
   }
   
}
