package logic.command;

import domain.Employee;
import domain.User;
import logic.service.UserServiceImpl;
import util.command.Command;

import java.sql.SQLException;
import java.util.Optional;

public class LoginCommand implements Command<Optional<User<Employee>>> {
   private final String username;
   private final char[] password;

   public LoginCommand(String username, char[] password) {
      this.username = username;
      this.password = password;
   }

   @Override
   public Optional<User<Employee>> execute() throws SQLException {
      return new UserServiceImpl().login(username, password);
   }
}
