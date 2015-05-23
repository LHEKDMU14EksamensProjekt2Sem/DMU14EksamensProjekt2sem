package logic.session.requestloan;

import com.ferrari.finances.dk.rki.Rating;
import domain.CarModel;
import domain.Customer;
import domain.Identity;
import logic.session.main.MainFacade;
import util.finance.Money;

import java.util.Optional;
import java.util.function.Consumer;

public class RequestLoanFacadeImpl implements RequestLoanFacade {
   private final MainFacade owner;
   private final CPRController cprController;
   private final CustomerDetailsController customerDetailsController;
   private final RequestDetailsController requestDetailsController;
   private RequestLoanView view;

   public RequestLoanFacadeImpl(MainFacade owner) {
      this.owner = owner;
      this.cprController = new CPRControllerImpl(this);
      this.customerDetailsController = new CustomerDetailsControllerImpl(this);
      this.requestDetailsController = new RequestDetailsControllerImpl(this);
   }

   @Override
   public RequestLoanView getView() {
      return view;
   }

   @Override
   public void setView(RequestLoanView view) {
      this.view = view;
   }

   // CPRController
   //////////////////

   @Override
   public Identity getIdentity() {
      return cprController.getIdentity();
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
   public void fetchCustomer(Consumer<Optional<Customer>> resultConsumer,
                             Consumer<Throwable> exceptionConsumer) {
      cprController.fetchCustomer(resultConsumer, exceptionConsumer);
   }

   @Override
   public void fetchCreditRating(Consumer<Rating> resultConsumer,
                                 Consumer<Throwable> exceptionConsumer) {
      cprController.fetchCreditRating(resultConsumer, exceptionConsumer);
   }

   // CustomerDetailsController
   //////////////////////////////

   @Override
   public void specifyFirstName(String firstName) {
      customerDetailsController.specifyFirstName(firstName);
   }

   @Override
   public void specifyLastName(String lastName) {
      customerDetailsController.specifyLastName(lastName);
   }

   @Override
   public void specifyStreet(String street) {
      customerDetailsController.specifyStreet(street);
   }

   @Override
   public void specifyPostalCode(String postalCode) {
      customerDetailsController.specifyPostalCode(postalCode);
   }

   @Override
   public void specifyPhone(String phone) {
      customerDetailsController.specifyPhone(phone);
   }

   @Override
   public void specifyEmail(String email) {
      customerDetailsController.specifyEmail(email);
   }

   @Override
   public void saveCustomer() {
      customerDetailsController.saveCustomer();
   }

   // RequestDetailsController
   /////////////////////////////

   @Override
   public void fetchCarModels() {
      requestDetailsController.fetchCarModels();
   }

   @Override
   public void fetchCars(CarModel model) {
      requestDetailsController.fetchCars(model);
   }

   @Override
   public Money getBasePrice() {
      return requestDetailsController.getBasePrice();
   }

   @Override
   public void specifyDiscount(String discount) {
      requestDetailsController.specifyDiscount(discount);
   }

   @Override
   public void specifyDiscountPct(String discountPct) {
      requestDetailsController.specifyDiscountPct(discountPct);
   }

   @Override
   public void specifySellingPrice(String sellingPrice) {
      requestDetailsController.specifySellingPrice(sellingPrice);
   }

   @Override
   public void specifyDownPayment(String downPayment) {
      requestDetailsController.specifyDownPayment(downPayment);
   }

   @Override
   public Money getLoanAmount() {
      return requestDetailsController.getLoanAmount();
   }

   @Override
   public void specifyPreferredRepayment(String prefRepayment) {
      requestDetailsController.specifyPreferredRepayment(prefRepayment);
   }

   @Override
   public void specifyPreferredTerm(String prefTerm) {
      requestDetailsController.specifyPreferredTerm(prefTerm);
   }

   @Override
   public void sendLoanRequest() {
      requestDetailsController.sendLoanRequest();
   }
}
