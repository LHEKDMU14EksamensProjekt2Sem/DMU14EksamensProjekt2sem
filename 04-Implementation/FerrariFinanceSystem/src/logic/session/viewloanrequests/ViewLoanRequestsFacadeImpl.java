package logic.session.viewloanrequests;

import logic.session.main.MainFacade;

public class ViewLoanRequestsFacadeImpl implements ViewLoanRequestsFacade {
   private final MainFacade owner;

   private ViewLoanRequestsViewToken currentView;

   public ViewLoanRequestsFacadeImpl(MainFacade owner) {
      this.owner = owner;
   }

   @Override
   public ViewLoanRequestsViewToken getViewToken() {
      return currentView;
   }

   @Override
   public void setViewToken(ViewLoanRequestsViewToken token) {
      currentView = token;
   }
}
