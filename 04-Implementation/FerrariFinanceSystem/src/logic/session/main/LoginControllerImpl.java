package logic.session.main;

import domain.Employee;
import logic.command.LoginCommand;
import util.auth.User;
import util.function.Callback;

public class LoginControllerImpl implements LoginController {
   private User<Employee> user;

   @Override
   public User<Employee> getUser() {
      return user;
   }

   @Override
   public boolean isLoggedIn() {
      return (user != null);
   }

   @Override
   public void login(String username, char[] password, Callback callback) {
      (new Thread(() -> {
         new LoginCommand(username, password).execute(r -> {
            user = r.getOptional().orElse(null);
            callback.call();
         });
      })).start();
   }
}
