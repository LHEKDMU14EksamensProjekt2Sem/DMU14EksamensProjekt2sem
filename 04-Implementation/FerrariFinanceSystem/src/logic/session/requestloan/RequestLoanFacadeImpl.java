package logic.session.requestloan;

import com.ferrari.finances.dk.rki.Rating;
import domain.Car;
import domain.CarModel;
import domain.Customer;
import domain.Employee;
import domain.Identity;
import domain.LoanOffer;
import domain.LoanRequest;
import domain.PostalCode;
import domain.User;
import exceptions.InvalidEmailException;
import exceptions.InvalidNameException;
import exceptions.InvalidPhoneException;
import exceptions.InvalidPostalCodeException;
import exceptions.InvalidStreetException;
import exceptions.StreetMissingHouseNumberException;
import exceptions.ValueRequiredException;
import logic.session.main.MainFacade;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class RequestLoanFacadeImpl implements RequestLoanFacade {
   private final MainFacade owner;
   private final CPRController cprCtrl;
   private final CustomerDetailsController customerDetailsCtrl;
   private final RequestDetailsController requestDetailsCtrl;
   private RequestLoanView view;

   public RequestLoanFacadeImpl(MainFacade owner) {
      this.owner = owner;
      this.cprCtrl = new CPRControllerImpl(this);
      this.customerDetailsCtrl = new CustomerDetailsControllerImpl(this);
      this.requestDetailsCtrl = new RequestDetailsControllerImpl(this);
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
   public User<Employee> getUser() {
      return owner.getUser();
   }

   // CPRController
   //////////////////

   @Override
   public Identity getIdentity() {
      return cprCtrl.getIdentity();
   }

   @Override
   public Rating getCreditRating() {
      return cprCtrl.getCreditRating();
   }

   @Override
   public boolean validateCPR(String cpr) {
      return cprCtrl.validateCPR(cpr);
   }

   @Override
   public void specifyCPR(String cpr) {
      cprCtrl.specifyCPR(cpr);
   }

   @Override
   public void fetchCreditRating(Consumer<Rating> resultConsumer,
                                 Consumer<Throwable> exceptionConsumer) {
      cprCtrl.fetchCreditRating(resultConsumer, exceptionConsumer);
   }

   // CustomerDetailsController
   //////////////////////////////

   @Override
   public Customer getCustomer() {
      return customerDetailsCtrl.getCustomer();
   }

   @Override
   public void specifyFirstName(String firstName) throws
           InvalidNameException, ValueRequiredException {
      customerDetailsCtrl.specifyFirstName(firstName);
   }

   @Override
   public void specifyLastName(String lastName) throws
           InvalidNameException, ValueRequiredException {
      customerDetailsCtrl.specifyLastName(lastName);
   }

   @Override
   public void specifyStreet(String street) throws
           InvalidStreetException, StreetMissingHouseNumberException, ValueRequiredException {
      customerDetailsCtrl.specifyStreet(street);
   }

   @Override
   public void specifyPostalCode(String postalCode,
                                 Consumer<Optional<PostalCode>> resultConsumer,
                                 Consumer<Throwable> exceptionConsumer) throws
           InvalidPostalCodeException, ValueRequiredException {
      customerDetailsCtrl.specifyPostalCode(postalCode, resultConsumer, exceptionConsumer);
   }

   @Override
   public void specifyPhone(String phone) throws
           InvalidPhoneException, ValueRequiredException {
      customerDetailsCtrl.specifyPhone(phone);
   }

   @Override
   public void specifyEmail(String email) throws
           InvalidEmailException, ValueRequiredException {
      customerDetailsCtrl.specifyEmail(email);
   }

   @Override
   public void fetchCustomer(Consumer<Optional<Customer>> resultConsumer,
                             Consumer<Throwable> exceptionConsumer) {
      customerDetailsCtrl.fetchCustomer(resultConsumer, exceptionConsumer);
   }

   // RequestDetailsController
   /////////////////////////////

   @Override
   public NumberFormat getMoneyFormat() {
      return requestDetailsCtrl.getMoneyFormat();
   }

   @Override
   public NumberFormat getPercentFormat() {
      return requestDetailsCtrl.getPercentFormat();
   }

   @Override
   public LoanRequest getLoanRequest() {
      return requestDetailsCtrl.getLoanRequest();
   }

   @Override
   public void fetchCarModels(Consumer<List<CarModel>> resultConsumer,
                              Consumer<Throwable> exceptionConsumer) {
      requestDetailsCtrl.fetchCarModels(resultConsumer, exceptionConsumer);
   }

   @Override
   public void fetchCars(CarModel model,
                         Consumer<List<Car>> resultConsumer,
                         Consumer<Throwable> exceptionConsumer) {
      requestDetailsCtrl.fetchCars(model, resultConsumer, exceptionConsumer);
   }

   @Override
   public void specifyCar(Car car) {
      requestDetailsCtrl.specifyCar(car);
   }

   @Override
   public void specifyDiscount(String discount) throws ParseException {
      requestDetailsCtrl.specifyDiscount(discount);
   }

   @Override
   public void specifyDiscountPct(String discountPct) throws ParseException {
      requestDetailsCtrl.specifyDiscountPct(discountPct);
   }

   @Override
   public void specifySellingPrice(String sellingPrice) throws ParseException {
      requestDetailsCtrl.specifySellingPrice(sellingPrice);
   }

   @Override
   public void specifyDownPayment(String downPayment) throws ParseException {
      requestDetailsCtrl.specifyDownPayment(downPayment);
   }

   @Override
   public void specifyDownPaymentPct(String downPaymentPct) throws ParseException {
      requestDetailsCtrl.specifyDownPaymentPct(downPaymentPct);
   }

   @Override
   public void specifyLoanAmount(String loanAmount) throws ParseException {
      requestDetailsCtrl.specifyLoanAmount(loanAmount);
   }

   @Override
   public void specifyPreferredRepayment(String prefRepayment) throws ParseException {
      requestDetailsCtrl.specifyPreferredRepayment(prefRepayment);
   }

   @Override
   public void specifyPreferredTerm(String prefTerm) throws ParseException {
      requestDetailsCtrl.specifyPreferredTerm(prefTerm);
   }

   @Override
   public void submitLoanRequest(Consumer<Optional<LoanOffer>> resultConsumer,
                                 Consumer<Throwable> exceptionConsumer) {
      requestDetailsCtrl.submitLoanRequest(resultConsumer, exceptionConsumer);
   }
}
