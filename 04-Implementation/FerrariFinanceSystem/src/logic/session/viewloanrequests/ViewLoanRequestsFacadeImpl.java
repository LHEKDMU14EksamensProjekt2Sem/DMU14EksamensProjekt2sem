package logic.session.viewloanrequests;

import com.ferrari.finances.dk.rki.Rating;
import domain.Employee;
import domain.LoanRequest;
import domain.LoanRequestStatus;
import domain.User;
import logic.format.GeneralDateFormat;
import logic.format.GeneralNumberFormat;
import logic.session.main.MainFacade;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ViewLoanRequestsFacadeImpl implements ViewLoanRequestsFacade {
   private final MainFacade owner;

   private ViewLoanRequestsViewToken currentView;
   private LoanRequestsController loanRequestsCtrl;

   public ViewLoanRequestsFacadeImpl(MainFacade owner) {
      this.owner = owner;
      loanRequestsCtrl = new LoanRequestsControllerImpl(this, owner);
   }

   @Override
   public User<Employee> getUser() {
      return owner.getUser();
   }

   @Override
   public GeneralNumberFormat getGeneralNumberFormat() {
      return owner.getGeneralNumberFormat();
   }

   @Override
   public GeneralDateFormat getGeneralDateFormat() {
      return owner.getGeneralDateFormat();
   }

   @Override
   public ViewLoanRequestsViewToken getViewToken() {
      return currentView;
   }

   @Override
   public void setViewToken(ViewLoanRequestsViewToken token) {
      currentView = token;
   }

   // LoanRequestsController
   ///////////////////////////

   @Override
   public boolean hasSelectedLoanRequest() {
      return loanRequestsCtrl.hasSelectedLoanRequest();
   }

   @Override
   public LoanRequest getSelectedLoanRequest() {
      return loanRequestsCtrl.getSelectedLoanRequest();
   }

   @Override
   public void setSelectedLoanRequest(LoanRequest loanRequest) {
      loanRequestsCtrl.setSelectedLoanRequest(loanRequest);
   }

   @Override
   public void setCreditRating(Rating rating) {
      loanRequestsCtrl.setCreditRating(rating);
   }

   @Override
   public boolean hasAcceptedCreditRating() {
      return loanRequestsCtrl.hasAcceptedCreditRating();
   }

   @Override
   public boolean hasOvernightRate() {
      return loanRequestsCtrl.hasOvernightRate();
   }

   @Override
   public void fetchLoanRequests(LoanRequestStatus status,
                                 Consumer<List<LoanRequest>> resultConsumer,
                                 Consumer<Throwable> exceptionConsumer) {
      loanRequestsCtrl.fetchLoanRequests(status, resultConsumer, exceptionConsumer);
   }

   @Override
   public void fetchLoanRequest(Consumer<Optional<LoanRequest>> resultConsumer,
                                Consumer<Throwable> exceptionConsumer) {
      loanRequestsCtrl.fetchLoanRequest(resultConsumer, exceptionConsumer);
   }

   @Override
   public void fetchCreditRating(Consumer<Rating> resultConsumer,
                                 Consumer<Throwable> exceptionConsumer) {
      loanRequestsCtrl.fetchCreditRating(resultConsumer, exceptionConsumer);
   }

   @Override
   public void fetchOvernightRate(Consumer<Double> resultConsumer,
                                  Consumer<Throwable> exceptionConsumer) {
      loanRequestsCtrl.fetchOvernightRate(resultConsumer, exceptionConsumer);
   }

   @Override
   public void approveLoanRequest(Consumer<Void> resultConsumer,
                                  Consumer<Throwable> exceptionConsumer) {
      loanRequestsCtrl.approveLoanRequest(resultConsumer, exceptionConsumer);
   }

   @Override
   public void declineLoanRequest(Consumer<Void> resultConsumer,
                                  Consumer<Throwable> exceptionConsumer) {
      loanRequestsCtrl.declineLoanRequest(resultConsumer, exceptionConsumer);
   }
}
