package dev.util;

import domain.Car;
import domain.CarComponent;
import domain.CarConfig;
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

   public static List<Car> newCars(List<CarConfig> configs) {
      return Arrays.asList(
              newCar(configs.get(0)),
              newCar(configs.get(0)),
              newCar(configs.get(1)),
              newCar(configs.get(2)),
              newCar(configs.get(3)),
              newCar(configs.get(3)),
              newCar(configs.get(3)),
              newCar(configs.get(4)),
              newCar(configs.get(5)),
              newCar(configs.get(6)),
              newCar(configs.get(6)),
              newCar(configs.get(7)),
              newCar(configs.get(7)));
   }

   private static Car newCar(CarConfig config) {
      Car car = new Car();
      car.setConfig(config);
      return car;
   }

   public static List<CarConfig> newCarConfigs(List<CarModel> models,
                                               List<CarComponent> components) {
      return Arrays.asList(
              newCarConfig(models.get(0), "Lion roar III", "Lorem ipsum", components.subList(0, 4)),
              newCarConfig(models.get(0), "Lion roar V", "Lorem ipsum", components.subList(4, 8)),
              newCarConfig(models.get(1), "Old Oak", "Lorem ipsum", components.subList(0, 4)),
              newCarConfig(models.get(2), "Spicey runner", "Lorem ipsum", components.subList(4, 8)),
              newCarConfig(models.get(3), "Warm Owl II", "Lorem ipsum", components.subList(0, 4)),
              newCarConfig(models.get(4), "Shifty", "Lorem ipsum", components.subList(4, 8)),
              newCarConfig(models.get(5), "Roadster I", "Lorem ipsum", components.subList(0, 4)),
              newCarConfig(models.get(5), "Roadster II", "Lorem ipsum", components.subList(4, 8)));
   }

   private static CarConfig newCarConfig(CarModel model,
                                         String name,
                                         String description,
                                         List<CarComponent> components) {
      CarConfig config = new CarConfig();
      config.setModel(model);
      config.setName(name);
      config.setDescription(description);
      config.setComponents(components);
      return config;
   }

   public static List<String> newCarComponentTypes() {
      return Arrays.asList(
              Type.PAINT.toString(),
              Type.RADIO.toString(),
              Type.ENGINE.toString(),
              Type.RIMS.toString());
   }

   public static List<CarComponent> newCarComponents() {
      return Arrays.asList(
              newCarComponent(Type.PAINT, "Pale yellow", "Lorem ipsum", "12000"),
              newCarComponent(Type.RADIO, "FM radio", "Lorem ipsum", "8000"),
              newCarComponent(Type.ENGINE, "V8 Inca", "Lorem ipsum", "210000"),
              newCarComponent(Type.RIMS, "Spike II", "Lorem ipsum", "43000"),
              newCarComponent(Type.PAINT, "Ferrari red", "Lorem ipsum", "32000"),
              newCarComponent(Type.RADIO, "Cassette player", "Lorem ipsum", "11500"),
              newCarComponent(Type.ENGINE, "V8 Thunder", "Lorem ipsum", "190000"),
              newCarComponent(Type.RIMS, "Magnets", "Lorem ipsum", "31000"));
   }

   private static CarComponent newCarComponent(Type type,
                                               String name,
                                               String description,
                                               String basePrice) {
      CarComponent comp = new CarComponent();
      comp.setType(type.toString());
      comp.setName(name);
      comp.setDescription(description);
      comp.setBasePrice(new Money(basePrice));
      return comp;
   }

   private enum Type {
      PAINT, RADIO, ENGINE, RIMS
   }
}
