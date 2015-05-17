package logic.session.main;

import domain.Employee;
import logic.session.requestloan.RequestLoanSessionFacade;
import util.auth.User;
import util.function.Callback;
import util.session.SessionFacade;

public interface MainSessionFacade extends
        SessionFacade<MainView>, LoginController {

   void login(String username, char[] password, Callback callback);

   boolean isLoggedIn();

   User<Employee> getUser();

   RequestLoanSessionFacade getRequestLoanSessionFacade();
}
