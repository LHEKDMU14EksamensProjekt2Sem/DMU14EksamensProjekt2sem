package logic.command;

import domain.Employee;
import domain.User;
import logic.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.Callable;

public class LoginCommand implements Callable<Optional<User<Employee>>> {
   private final String username;
   private final char[] password;

   public LoginCommand(String username, char[] password) {
      this.username = username;
      this.password = password;
   }

   @Override
   public Optional<User<Employee>> call() throws SQLException {
      return new UserServiceImpl().login(username, password);
   }
}
