package logic.session.requestloan;

import java.util.concurrent.ExecutorService;

public class RequestLoanSessionFacadeImpl implements RequestLoanSessionFacade {
   private final ExecutorService executor;
   private RequestLoanView view;

   public RequestLoanSessionFacadeImpl(ExecutorService executor) {
      this.executor = executor;
   }

   @Override
   public RequestLoanView getView() {
      return view;
   }

   @Override
   public void setView(RequestLoanView view) {
      this.view = view;
   }
}
