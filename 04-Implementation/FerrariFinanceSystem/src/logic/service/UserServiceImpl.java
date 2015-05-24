package logic.service;

import domain.Employee;
import domain.EmployeeRole;
import domain.Person;
import domain.User;

import java.sql.SQLException;
import java.util.Optional;

public class UserServiceImpl {
   public Optional<User<Employee>> login(String username, char[] password) throws SQLException {
      Person p = new Person();
      p.setFirstName("John");
      p.setLastName("Doe");

      Employee em = new Employee();
      em.setPerson(p);
      em.setRole(EmployeeRole.SALESMAN);

      User<Employee> user = new User<>();
      user.setEntity(em);
      user.setUsername("jodo");

      return Optional.ofNullable(user);
   }
}
