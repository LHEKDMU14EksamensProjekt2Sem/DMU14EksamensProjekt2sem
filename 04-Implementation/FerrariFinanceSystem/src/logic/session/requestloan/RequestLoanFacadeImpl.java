package logic.session.requestloan;

import com.ferrari.finances.dk.rki.Rating;
import domain.Customer;
import domain.Identity;
import util.command.Receiver;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;

public class RequestLoanFacadeImpl implements RequestLoanFacade {
   private final Executor executor;
   private final CPRController cprController;
   private final RequestDetailsController loanRequestController;
   private RequestLoanView view;

   public RequestLoanFacadeImpl(Executor executor) {
      this.executor = executor;
      this.cprController = new CPRControllerImpl(this);
      this.loanRequestController = new RequestDetailsControllerImpl();
   }

   @Override
   public Executor getExecutor() {
      return executor;
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
   public void fetchCars(Object car) {
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

   // CPRController
   //////////////////

   @Override
   public Identity getIdentity() {
      return cprController.getIdentity();
   }

   @Override
   public Customer getCustomer() {
      return cprController.getCustomer();
   }

   @Override
   public Rating getCreditRating() {
      return cprController.getCreditRating();
   }

   @Override
   public void specifyCPR(String cpr) {
      cprController.specifyCPR(cpr);
   }

   @Override
   public void fetchCustomer(Receiver<Optional<Customer>> resultReceiver,
                             Receiver<Exception> faultReceiver) {
      cprController.fetchCustomer(resultReceiver, faultReceiver);
   }

   @Override
   public void fetchCreditRating(Receiver<Rating> receiver) {
      cprController.fetchCreditRating(receiver);
   }
}
