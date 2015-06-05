package logic.session.main;

import domain.Employee;
import domain.User;
import logic.format.GeneralNumberFormat;
import logic.session.createloanrequest.CreateLoanRequestFacade;
import logic.session.createloanrequest.CreateLoanRequestFacadeImpl;

import java.util.Optional;
import java.util.function.Consumer;

public class MainFacadeImpl implements MainFacade {
   private final MainController mainCtrl;
   private final LoginController loginCtrl;
   private MainViewToken view;

   public MainFacadeImpl() {
      mainCtrl = new MainControllerImpl(this);
      loginCtrl = new LoginControllerImpl(this);
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
      return mainCtrl.getGeneralNumberFormat();
   }

   // LoginController
   ////////////////////

   @Override
   public User<Employee> getUser() {
      return loginCtrl.getUser();
   }

   @Override
   public boolean isLoggedIn() {
      return loginCtrl.isLoggedIn();
   }

   @Override
   public void login(String username, char[] password,
                     Consumer<Optional<User<Employee>>> resultConsumer,
                     Consumer<Throwable> exceptionConsumer) {
      loginCtrl.login(username, password, resultConsumer, exceptionConsumer);
   }
}
