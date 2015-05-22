package logic.session.main;

import domain.Employee;
import logic.session.requestloan.RequestLoanFacade;
import logic.session.requestloan.RequestLoanFacadeImpl;
import util.auth.User;
import util.command.Receiver;

import java.util.Optional;

public class MainFacadeImpl implements MainFacade {
   private final LoginController loginController;
   private MainView view;

   public MainFacadeImpl() {
      loginController = new LoginControllerImpl(this);
   }

   @Override
   public RequestLoanFacade newRequestLoanFacade() {
      return new RequestLoanFacadeImpl(this);
   }

   @Override
   public MainView getView() {
      return view;
   }

   @Override
   public void setView(MainView view) {
      this.view = view;
   }

   // LoginController
   ////////////////////

   @Override
   public User<Employee> getUser() {
      return loginController.getUser();
   }

   @Override
   public boolean isLoggedIn() {
      return loginController.isLoggedIn();
   }

   @Override
   public void login(String username, char[] password,
                     Receiver<Optional<User<Employee>>> resultReceiver,
                     Receiver<Throwable> exceptionReceiver) {
      loginController.login(username, password, resultReceiver, exceptionReceiver);
   }
}
