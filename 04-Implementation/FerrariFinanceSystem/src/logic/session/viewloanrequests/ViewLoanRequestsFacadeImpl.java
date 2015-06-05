package logic.session.viewloanrequests;

import domain.LoanRequest;
import logic.format.GeneralDateFormat;
import logic.format.GeneralNumberFormat;
import logic.session.main.MainFacade;

import java.util.List;
import java.util.function.Consumer;

public class ViewLoanRequestsFacadeImpl implements ViewLoanRequestsFacade {
   private final MainFacade owner;

   private ViewLoanRequestsViewToken currentView;
   private LoanRequestsController loanRequestsCtrl;

   public ViewLoanRequestsFacadeImpl(MainFacade owner) {
      this.owner = owner;
      loanRequestsCtrl = new LoanRequestsControllerImpl(this);
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
   public void fetchLoanRequests(Consumer<List<LoanRequest>> resultConsumer,
                                 Consumer<Throwable> exceptionConsumer) {
      loanRequestsCtrl.fetchLoanRequests(resultConsumer, exceptionConsumer);
   }
}
