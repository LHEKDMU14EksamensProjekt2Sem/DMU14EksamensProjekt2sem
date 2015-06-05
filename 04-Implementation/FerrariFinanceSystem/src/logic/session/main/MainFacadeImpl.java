package logic.session.main;

import domain.Employee;
import domain.User;
import logic.format.GeneralNumberFormat;
import logic.session.createloanrequest.CreateLoanRequestFacade;
import logic.session.createloanrequest.CreateLoanRequestFacadeImpl;

import java.util.Optional;
import java.util.function.Consumer;

public class MainFacadeImpl implements MainFacade {
   private final MainController mainController;
   private final LoginController loginController;
   private MainViewToken view;

   public MainFacadeImpl() {
      mainController = new MainControllerImpl(this);
      loginController = new LoginControllerImpl(this);
   }

   @Override
   public CreateLoanRequestFacade newCreateLoanRequestFacade() {
      return new CreateLoanRequestFacadeImpl(this);
   }

   @Override
   public MainViewToken getViewToken() {
      return view;
   }

   @Override
   public void setViewToken(MainViewToken view) {
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
