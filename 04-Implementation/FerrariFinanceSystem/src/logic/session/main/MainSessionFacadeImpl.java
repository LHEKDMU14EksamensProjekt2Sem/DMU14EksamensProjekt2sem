package logic.session.main;

import domain.Employee;
import logic.session.requestloan.RequestLoanSessionFacade;
import logic.session.requestloan.RequestLoanSessionFacadeImpl;
import util.auth.User;
import util.command.Receiver;

import java.util.Optional;
import java.util.concurrent.Executor;

public class MainSessionFacadeImpl implements MainSessionFacade {
   private final Executor executor;
   private final LoginController loginController;
   private MainView view;

   public MainSessionFacadeImpl(Executor executor) {
      this.executor = executor;
      loginController = new LoginControllerImpl(this);
   }

   @Override
   public Executor getExecutor() {
      return executor;
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
   public void login(String username, char[] password,
                     Receiver<Optional<User<Employee>>> resultReceiver,
                     Receiver<Exception> faultReceiver) {
      loginController.login(username, password, resultReceiver, faultReceiver);
   }

   @Override
   public RequestLoanSessionFacade getRequestLoanSessionFacade() {
      return new RequestLoanSessionFacadeImpl(executor);
   }
}
