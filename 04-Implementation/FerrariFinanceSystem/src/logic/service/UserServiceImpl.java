package logic.service;

import domain.Employee;
import domain.User;

import java.sql.SQLException;
import java.util.Optional;

public class UserServiceImpl {
   public Optional<User<Employee>> login(String username, char[] password) throws SQLException {
      Employee em = new EmployeeServiceImpl().readEmployee(1).get();

      User<Employee> user = new User<>();
      user.setEntity(em);
      user.setUsername("jodo");

      return Optional.ofNullable(user);
   }
}
