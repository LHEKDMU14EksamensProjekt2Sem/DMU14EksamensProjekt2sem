package dev.util;

import domain.CarModel;
import domain.Customer;
import domain.Employee;
import domain.EmployeeRole;
import domain.Person;
import domain.PostalCode;
import util.finance.Money;

import java.util.Arrays;
import java.util.List;

import static domain.EmployeeRole.*;

public class SampleUtil {
   public static List<Employee> newEmployees() {
      return Arrays.asList(
              newEmployee(newPerson(
                              "Alice", "Wunderbaum",
                              "Wonderland 42", newPostalCode(7400),
                              12345678, "alice@wonderland.com"),
                      SALES_MANAGER),
              newEmployee(newPerson(
                              "Bob", "da Bass",
                              "Bassheadvej 164", newPostalCode(7800),
                              88888888, "bob@mixcloud.com"),
                      SALESMAN));
   }

   public static List<String> newEmployeeCPRs() {
      return Arrays.asList(
              "9876543210",
              "0099887766");
   }

   private static Employee newEmployee(Person p, EmployeeRole role) {
      Employee em = new Employee();
      em.setPerson(p);
      em.setRole(role);
      return em;
   }

   public static List<Customer> newCustomers() {
      return Arrays.asList(
              newCustomer(newPerson(
                              "Hero", "Man",
                              "Marble 5", newPostalCode(1000),
                              55532877, "hero@marble.com"),
                      true),
              newCustomer(newPerson(
                              "Julem", "Anden",
                              "Coladiditvej 102", newPostalCode(2412),
                              97851992, "jule@and.com"),
                      false));
   }

   public static List<String> newCustomerCPRs() {
      return Arrays.asList(
              "0000000001",
              "2011772122");
   }

   private static Customer newCustomer(Person p, boolean standing) {
      Customer c = new Customer();
      c.setPerson(p);
      c.setStanding(standing);
      return c;
   }

   private static Person newPerson(String first, String last,
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

   private static PostalCode newPostalCode(int postalCode) {
      PostalCode pc = new PostalCode();
      pc.setPostalCode(postalCode);
      return pc;
   }

   public static List<CarModel> newCarModels() {
      return Arrays.asList(
              newCarModel(2014, "Ferrari California T", "description", "3395000"),
              newCarModel(2015, "488 GTB", "description", "7459000.00"),
              newCarModel(2010, "458 ITALIA", "description", "4390000.00"),
              newCarModel(2013, "F12BERLINETTA", "description", "5905000.00"),
              newCarModel(2011, "FF", "description", "5280000.00"),
              newCarModel(2013, "LaFERRARI", "description", "2995000.00"));
   }

   private static CarModel newCarModel(int year, String name, String description, String price) {
      CarModel m = new CarModel();
      m.setYear(year);
      m.setName(name);
      m.setDescription(description);
      m.setBasePrice(new Money(price));
      return m;
   }
   
   public static List<CarComponantType> newCarComponantTypes(){
      return Arrays.asList(
            newCarComponantType("Paint"),
            newCarComponantType("Radio"),
            newCarComponantType("Engine"),
            newCarComponantType("Rims"),)
   }
   
   private static CarComponantType newCarComponantType(String type){
      CarComponantType cpt = new CarComponantType();
      cpt.setType(type);
      return cpt;
   }

   
   private static List<CarComponent> newCarComponant(){
      return Arrays.asList(
            )
            
   }
   
   private static CarComponant newCarComponant()
}
