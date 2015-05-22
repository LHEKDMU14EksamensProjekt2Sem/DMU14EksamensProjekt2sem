package logic.session.main;

import domain.Employee;
import util.auth.User;
import util.command.Receiver;

import java.util.Optional;

public interface LoginController {
   User<Employee> getUser();

   boolean isLoggedIn();

   void login(String username, char[] password,
              Receiver<Optional<User<Employee>>> resultReceiver,
              Receiver<Throwable> exceptionReceiver);
}
