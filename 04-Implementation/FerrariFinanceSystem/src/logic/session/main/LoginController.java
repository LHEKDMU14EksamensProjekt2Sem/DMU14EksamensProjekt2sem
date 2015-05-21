package logic.session.main;

import domain.Employee;
import util.auth.User;
import util.command.Callback;

import java.util.Optional;

public interface LoginController {
   void login(String username, char[] password, Callback<Optional<User<Employee>>, Void> callback);

   boolean isLoggedIn();

   User<Employee> getUser();
}
