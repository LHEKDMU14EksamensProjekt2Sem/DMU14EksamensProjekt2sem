package logic.session.main;

import domain.Employee;
import util.auth.User;
import util.function.Callback;

public interface LoginController {
   void login(String username, char[] password, Callback callback);

   boolean isLoggedIn();

   User<Employee> getUser();
}
