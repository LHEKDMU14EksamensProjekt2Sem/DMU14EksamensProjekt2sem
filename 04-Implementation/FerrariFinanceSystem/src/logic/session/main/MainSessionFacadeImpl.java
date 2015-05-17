package logic.session.main;

import domain.Employee;
import logic.session.requestloan.RequestLoanSessionFacade;
import logic.session.requestloan.RequestLoanSessionFacadeImpl;
import util.auth.User;
import util.function.Callback;

public class MainSessionFacadeImpl implements MainSessionFacade {
   private MainView view;

   private LoginController loginController;

   public MainSessionFacadeImpl() {
      loginController = new LoginControllerImpl();
   }

   @Override
   public MainView getView() {
      return view;
   }

   @Override
   public void setView(MainView view) {
      this.view = view;
   }

   @Override
   public User<Employee> getUser() {
      return loginController.getUser();
   }

   @Override
   public boolean isLoggedIn() {
      return loginController.isLoggedIn();
   }

   @Override
   public void login(String username, char[] password, Callback callback) {
      loginController.login(username, password, callback);
   }

   @Override
   public RequestLoanSessionFacade getRequestLoanSessionFacade() {
      return new RequestLoanSessionFacadeImpl();
   }
}
