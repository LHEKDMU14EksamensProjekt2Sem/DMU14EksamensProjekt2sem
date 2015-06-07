package logic.session.main;

import com.ferrari.finances.dk.rki.Rating;
import domain.Employee;
import domain.Identity;
import domain.User;
import logic.format.GeneralDateFormat;
import logic.format.GeneralNumberFormat;
import logic.session.createloanrequest.CreateLoanRequestFacade;
import logic.session.createloanrequest.CreateLoanRequestFacadeImpl;
import logic.session.viewloanoffers.ViewLoanOffersFacade;
import logic.session.viewloanoffers.ViewLoanOffersFacadeImpl;
import logic.session.viewloanrequests.ViewLoanRequestsFacade;
import logic.session.viewloanrequests.ViewLoanRequestsFacadeImpl;

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
   public ViewLoanRequestsFacade newViewLoanRequestsFacade() {
      return new ViewLoanRequestsFacadeImpl(this);
   }

   @Override
   public ViewLoanOffersFacade newViewLoanOffersFacade() {
      return new ViewLoanOffersFacadeImpl(this);
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

   @Override
   public GeneralDateFormat getGeneralDateFormat() {
      return mainCtrl.getGeneralDateFormat();
   }

   @Override
   public void fetchCreditRating(Identity identity,
                                 Consumer<Rating> resultConsumer,
                                 Consumer<Throwable> exceptionConsumer) {
      mainCtrl.fetchCreditRating(identity, resultConsumer, exceptionConsumer);
   }

   @Override
   public void fetchOvernightRate(Consumer<Double> resultConsumer,
                                  Consumer<Throwable> exceptionConsumer) {
      mainCtrl.fetchOvernightRate(resultConsumer, exceptionConsumer);
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
