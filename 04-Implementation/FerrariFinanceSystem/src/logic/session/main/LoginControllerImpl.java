package logic.session.main;

import domain.Employee;
import logic.command.LoginCommand;
import util.auth.User;
import util.command.Receiver;
import util.command.SwingCommand;

import java.util.Optional;

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
                     Receiver<Optional<User<Employee>>> resultReceiver,
                     Receiver<Throwable> exceptionReceiver) {
      new SwingCommand<>(
              new LoginCommand(username, password),
              r -> {
                 user = r.orElse(null);
                 resultReceiver.receive(r);
              },
              exceptionReceiver::receive
      ).execute();
   }
}
