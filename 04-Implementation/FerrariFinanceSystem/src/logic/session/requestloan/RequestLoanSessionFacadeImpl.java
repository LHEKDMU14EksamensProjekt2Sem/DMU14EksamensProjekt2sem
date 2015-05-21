package logic.session.requestloan;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class RequestLoanSessionFacadeImpl implements RequestLoanSessionFacade {
   private final ExecutorService executor;
   private RequestLoanView view;
   private LoanRequestDetailsController loanRequestController;

   public RequestLoanSessionFacadeImpl(ExecutorService executor) {
      this.executor = executor;
      this.loanRequestController = new LoanRequestDetailsControllerImpl();
   }

   @Override
   public RequestLoanView getView() {
      return view;
   }

   @Override
   public void setView(RequestLoanView view) {
      this.view = view;
   }

   @Override
   public void fetchModels() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public List getModels() {
      // TODO Auto-generated method stub
      return loanRequestController.getModels();
   }

   @Override
   public void fetchCars( Object car ) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public List getCars() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public double getSalesprice() {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public void setDownpayment() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public double getFinancing() {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public void save() {
      // TODO Auto-generated method stub
      
   }
 
}
