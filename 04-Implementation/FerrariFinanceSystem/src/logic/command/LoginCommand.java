package logic.command;

import domain.Employee;
import util.auth.User;
import util.auth.UserAuth;
import util.command.AsyncCommand;
import util.command.Callback;

import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.Executor;

public class LoginCommand extends AsyncCommand {
   private final String username;
   private final char[] password;
   private final Callback<Optional<User<Employee>>, SQLException> callback;

   public LoginCommand(Executor executor, String username, char[] password,
                       Callback<Optional<User<Employee>>, SQLException> callback) {
      super(executor);
      this.username = username;
      this.password = password;
      this.callback = callback;
   }

   @Override
   public void run() {
      try {
         callback.success(
                 new UserAuth().login(username, password));
      } catch (SQLException e) {
         callback.failure(e);
      }
   }
}
