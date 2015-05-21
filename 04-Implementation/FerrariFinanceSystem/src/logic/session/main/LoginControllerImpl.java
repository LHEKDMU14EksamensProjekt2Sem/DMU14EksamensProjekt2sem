package logic.session.main;

import domain.Employee;
import logic.command.LoginCommand;
import util.auth.User;
import util.command.Callback;

import java.sql.SQLException;
import java.util.Optional;

public class LoginControllerImpl implements LoginController {
   private final MainSessionFacade facade;
   private User<Employee> user;

   public LoginControllerImpl(MainSessionFacade facade) {
      this.facade = facade;
   }

   @Override
   public User<Employee> getUser() {
      return user;
   }

   @Override
   public boolean isLoggedIn() {
      return (user != null);
   }

   @Override
   public void login(String username, char[] password,
                     Callback<Optional<User<Employee>>, Void> callback) {
      new LoginCommand(facade.getExecutor(), username, password,
              new Callback<Optional<User<Employee>>, SQLException>() {
                 @Override
                 public void success(Optional<User<Employee>> result) {
                    user = result.orElse(null);
                    callback.success(result);
                 }

                 @Override
                 public void failure(SQLException exception) {
                    callback.failure(null);
                 }
              }).execute();
   }
}
