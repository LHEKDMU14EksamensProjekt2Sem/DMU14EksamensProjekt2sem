package dev.command;

import dev.sample.UserSample;
import domain.Employee;
import domain.User;
import logic.service.UserServiceImpl;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

public class CreateUserSampleCommand implements Callable<List<User<Employee>>> {
   private final List<Employee> employees;
   private final ConnectionHandler con;

   public CreateUserSampleCommand(List<Employee> employees, ConnectionHandler con) {
      this.employees = employees;
      this.con = con;
   }

   @Override
   public List<User<Employee>> call() throws SQLException {
      return createUsers();
   }

   private List<User<Employee>> createUsers() throws SQLException {
      List<User<Employee>> users = UserSample.newUsers(employees);
      List<char[]> pwds = UserSample.newPasswords();
      new UserServiceImpl().createUsers(users, pwds, con);
      return users;
   }
}
