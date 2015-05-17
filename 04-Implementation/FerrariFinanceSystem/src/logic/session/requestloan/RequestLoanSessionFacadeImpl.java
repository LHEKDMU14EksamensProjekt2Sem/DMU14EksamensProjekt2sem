package logic.session.requestloan;

public class RequestLoanSessionFacadeImpl implements RequestLoanSessionFacade {
   private RequestLoanView view;

   @Override
   public RequestLoanView getView() {
      return view;
   }

   @Override
   public void setView(RequestLoanView view) {
      this.view = view;
   }
}
