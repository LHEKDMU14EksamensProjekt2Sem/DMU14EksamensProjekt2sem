package logic.session.main;

import domain.Employee;
import util.auth.User;

import java.util.Optional;
import java.util.function.Consumer;

public interface LoginController {
   User<Employee> getUser();

   boolean isLoggedIn();

   void login(String username, char[] password,
              Consumer<Optional<User<Employee>>> resultConsumer,
              Consumer<Throwable> exceptionConsumer);
}
