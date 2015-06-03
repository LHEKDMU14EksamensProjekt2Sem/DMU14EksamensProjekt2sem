package logic.session.main;

import domain.Employee;
import domain.User;
import logic.format.GeneralNumberFormat;
import logic.session.requestloan.RequestLoanFacade;
import logic.session.requestloan.RequestLoanFacadeImpl;

import java.util.Optional;
import java.util.function.Consumer;

public class MainFacadeImpl implements MainFacade {
   private final MainController mainController;
   private final LoginController loginController;
   private MainView view;

   public MainFacadeImpl() {
      mainController = new MainControllerImpl(this);
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

   // MainController
   ///////////////////

   @Override
   public GeneralNumberFormat getGeneralNumberFormat() {
      return mainController.getGeneralNumberFormat();
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
                     Consumer<Optional<User<Employee>>> resultConsumer,
                     Consumer<Throwable> exceptionConsumer) {
      loginController.login(username, password, resultConsumer, exceptionConsumer);
   }
}
