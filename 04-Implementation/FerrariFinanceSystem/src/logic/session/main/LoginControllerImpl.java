package logic.session.main;

import domain.Employee;
import logic.command.LoginCommand;
import util.auth.User;
import util.command.Receiver;

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
                     Receiver<Optional<User<Employee>>> resultReceiver,
                     Receiver<Exception> faultReceiver) {
      facade.getExecutor().execute(
              new LoginCommand(username, password,
                      r -> {
                         user = r.orElse(null);
                         resultReceiver.receive(r);
                      },
                      faultReceiver::receive
              ));
   }
}
