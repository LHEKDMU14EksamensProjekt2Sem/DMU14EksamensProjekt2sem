package dev.command;

import dev.util.SampleUtil;
import domain.Car;
import domain.CarComponent;
import domain.CarConfig;
import domain.CarModel;
import domain.Customer;
import domain.Employee;
import domain.Identity;
import logic.service.*;
import util.command.Command;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;

public class CreateSampleCommand implements Command<Void> {
   private final ConnectionHandler con;

   public CreateSampleCommand(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public Void execute() throws SQLException {
      createEmployees();
      createCustomers();
      List<CarModel> models = createCarModels();
      createCarComponentTypes();
      List<CarComponent> components = createCarComponents();
      List<CarConfig> configs = createCarConfigs(models, components);
      createCars(configs);
      return null;
   }

   private void createEmployees() throws SQLException {
      List<Employee> employees = SampleUtil.newEmployees();
      List<String> cprs = SampleUtil.newEmployeeCPRs();
      EmployeeService logic = new EmployeeServiceImpl();
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
      CustomerService logic = new CustomerServiceImpl();
      for (int i = 0; i < cprs.size(); i++) {
         Customer c = customers.get(i);
         Identity identity = new Identity();
         identity.setCPR(cprs.get(i));
         identity.setPerson(c.getPerson());
         logic.createCustomer(c, identity, con);
      }
   }

   private List<CarModel> createCarModels() throws SQLException {
      List<CarModel> models = SampleUtil.newCarModels();
      CarModelService logic = new CarModelServiceImpl();
      logic.createCarModels(models, con);
      return models;
   }

   private void createCarComponentTypes() throws SQLException {
      List<String> types = SampleUtil.newCarComponentTypes();
      CarComponentTypeService service = new CarComponentTypeServiceImpl();
      service.createCarComponentTypes(types, con);
   }

   private List<CarComponent> createCarComponents() throws SQLException {
      List<CarComponent> components = SampleUtil.newCarComponents();
      CarComponentService service = new CarComponentServiceImpl();
      service.createCarComponents(components, con);
      return components;
   }

   private List<CarConfig> createCarConfigs(List<CarModel> models,
                                            List<CarComponent> components) throws SQLException {
      List<CarConfig> configs = SampleUtil.newCarConfigs(models, components);
      CarConfigService service = new CarConfigServiceImpl();
      service.createCarConfigs(configs, con);
      return configs;
   }

   private void createCars(List<CarConfig> configs) throws SQLException {
      List<Car> cars = SampleUtil.newCars(configs);
      CarService service = new CarServiceImpl();
      service.createCars(cars, con);
   }
}
