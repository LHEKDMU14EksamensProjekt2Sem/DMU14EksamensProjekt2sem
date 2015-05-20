package logic.command;

import domain.Customer;
import domain.Employee;
import domain.Person;
import domain.PostalCode;
import domain.CarModel;
import util.finance.Money;
import logic.EmployeeRole;
import logic.entity.CustomerLogic;
import logic.entity.EmployeeLogic;
import util.function.Command;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;

public class StoreSampleDataCommand implements Command {
   private final ConnectionHandler con;

   public StoreSampleDataCommand(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void execute() throws SQLException {
      createEmployees(con);
      createCustomers(con);
   }

   private void createEmployees(ConnectionHandler con) throws SQLException {
      EmployeeLogic logic = new EmployeeLogic();
      for (Employee em : newEmployees()) {
         logic.createEmployee(em, con);
      }
   }

   private void createCustomers(ConnectionHandler con) throws SQLException {
      CustomerLogic logic = new CustomerLogic();
      Customer[] customers = newCustomers();
      String[] cprs = new String[]{ "0000000001", "2011772122" };
      for (int i = 0; i < cprs.length; i++) {
         logic.createCustomer(customers[i], cprs[i], con);
      }
   }
   

   private Employee[] newEmployees() {
      return new Employee[]{
              newEmployee(newPerson(
                              "Alice", "Wunderbaum",
                              "Wonderland 42", newPostalCode(7400),
                              12345678, "alice@wonderland.com"),
                      EmployeeRole.SALES_MANAGER),
              newEmployee(newPerson(
                              "Bob", "da Bass",
                              "Bassheadvej 164", newPostalCode(7800),
                              88888888, "bob@mixcloud.com"),
                      EmployeeRole.SALESMAN)
      };
   }

   private Customer[] newCustomers() {
      return new Customer[]{
              newCustomer(newPerson(
                              "Hero", "Man",
                              "Marble 5", newPostalCode(1000),
                              55532877, "hero@marble.com"),
                      true),
              newCustomer(newPerson(
                              "Julem", "Anden",
                              "Coladiditvej 102", newPostalCode(2412),
                              97851992, "jule@and.com"),
                      false)
      };
   }
   
//   private CarModel[] newModels() {
//      return new CarModel[]{
//            newModel(2014, "Ferrari California T", "description", "3395000"),
//            newModel(2015, "488 GTB", "description", "7459000.00" ),
//            newModel(2010, "458 ITALIA", "description", "4390000.00" ),
//            newModel(2013, "F12BERLINETTA", "description", "5905000.00"),
//            newModel(2011, "FF", "description", "5280000.00"),
//            newModel(2013, "LaFERRARI", "description", "2995000.00")
//      };
//   }

   private Employee newEmployee(Person p, EmployeeRole role) {
      Employee em = new Employee();
      em.setPerson(p);
      em.setRole(role);
      return em;
   }

   private Customer newCustomer(Person p, boolean standing) {
      Customer c = new Customer();
      c.setPerson(p);
      c.setStanding(standing);
      return c;
   }

   private Person newPerson(String first, String last,
                            String street, PostalCode pc,
                            int phone, String email) {
      Person p = new Person();
      p.setFirstName(first);
      p.setLastName(last);
      p.setStreet(street);
      p.setPostalCode(pc);
      p.setPhone(phone);
      p.setEmail(email);
      return p;
   }

   private PostalCode newPostalCode(int postalCode) {
      PostalCode pc = new PostalCode();
      pc.setPostalCode(postalCode);
      return pc;
   }
   
   private CarModel newModel(int year, String name, String description, Money price) {
      CarModel m = new CarModel();
      m.setYear(year);
      m.setName(name);
      m.setDescription(description);
      m.setBasePrice(price);
      return m;
      
   }
}
