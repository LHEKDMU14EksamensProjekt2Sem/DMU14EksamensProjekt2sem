package logic.session.main;

import domain.Employee;
import logic.command.LoginCommand;
import util.auth.User;
import util.command.SwingCommand;

import java.util.Optional;
import java.util.function.Consumer;

public class LoginControllerImpl implements LoginController {
   private final MainFacade facade;
   private User<Employee> user;

   public LoginControllerImpl(MainFacade facade) {
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
                     Consumer<Optional<User<Employee>>> resultConsumer,
                     Consumer<Throwable> exceptionConsumer) {
      new SwingCommand<>(
              new LoginCommand(username, password),
              r -> {
                 user = r.orElse(null);
                 resultConsumer.accept(r);
              },
              exceptionConsumer::accept
      ).execute();
   }
}
