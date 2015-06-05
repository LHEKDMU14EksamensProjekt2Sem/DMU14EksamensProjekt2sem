package logic.session.createloanrequest;

import domain.Car;
import domain.CarModel;
import domain.Employee;
import domain.LoanOffer;
import domain.LoanRequest;
import domain.LoanRequestStatus;
import domain.Sale;
import exceptions.DiscountPctTooHighException;
import exceptions.DownPaymentPctTooLowException;
import exceptions.TermTooLongException;
import exceptions.ValueRequiredException;
import logic.command.FetchCarModelsCommand;
import logic.command.FetchCarsCommand;
import logic.command.SubmitLoanRequestCommand;
import logic.session.createloanrequest.validation.RequestDetailsValidator;
import logic.session.createloanrequest.validation.RequestDetailsValidatorImpl;
import util.finance.Money;
import util.swing.SwingCommand;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class RequestDetailsControllerImpl implements RequestDetailsController {
   private final CreateLoanRequestFacade facade;
   private final RequestDetailsValidator validator;
   private final LoanRequest loanRequest;
   private final Sale sale;

   public RequestDetailsControllerImpl(CreateLoanRequestFacade facade) {
      this.facade = facade;

      validator = new RequestDetailsValidatorImpl(facade.getGeneralNumberFormat());

      Employee employee = facade.getUser().getEntity();

      sale = new Sale();
      sale.setSeller(employee);
      sale.setCustomer(facade.getCustomer());
      sale.setBasePrice(Money.ZERO);
      sale.setSellingPrice(Money.ZERO);

      loanRequest = new LoanRequest();
      loanRequest.setSale(sale);
      loanRequest.setStatus(LoanRequestStatus.PENDING);
      loanRequest.setStatusByEmployee(employee);
      loanRequest.setDate(LocalDate.now());
      loanRequest.setLoanAmount(Money.ZERO);
   }

   @Override
   public LoanRequest getLoanRequest() {
      return loanRequest;
   }

   @Override
   public void fetchCarModels(Consumer<List<CarModel>> resultConsumer,
                              Consumer<Throwable> exceptionConsumer) {
      new SwingCommand<>(
              new FetchCarModelsCommand(),
              resultConsumer::accept,
              exceptionConsumer::accept
      ).execute();
   }

   @Override
   public void fetchCars(CarModel model,
                         Consumer<List<Car>> resultConsumer,
                         Consumer<Throwable> exceptionConsumer) {
      sale.setCar(null);
      new SwingCommand<>(
              new FetchCarsCommand(model),
              resultConsumer::accept,
              exceptionConsumer::accept
      ).execute();
   }

   @Override
   public void specifyCar(Car car) {
      // Maintain current discount pct
      // and down payment pct
      double discountPct = loanRequest.getSale().getDiscountPct();
      double downPaymentPct = loanRequest.getDownPaymentPct();

      sale.setCar(car);

      // Reapply discount pct and
      // down payment pct, in order
      loanRequest.getSale().setDiscountPct(discountPct);
      loanRequest.setDownPaymentPct(
              Math.max(downPaymentPct, validator.getMinDownPaymentPct()));
   }

   @Override
   public void specifyDiscount(String discount) throws ParseException {
      // Maintain current down payment pct
      double downPaymentPct = loanRequest.getDownPaymentPct();

      Sale sale = loanRequest.getSale();
      try {
         Money value = validateDiscount(discount);
         sale.setDiscount(value);
      } catch (DiscountPctTooHighException e) {
         sale.setDiscountPct(validator.getMaxDiscountPct());
      }

      // Reapply down payment pct
      loanRequest.setDownPaymentPct(downPaymentPct);
   }

   @Override
   public void specifyDiscountPct(String discountPct) throws ParseException {
      // Maintain current down payment pct
      double downPaymentPct = loanRequest.getDownPaymentPct();

      Sale sale = loanRequest.getSale();
      double value;
      try {
         value = validateDiscountPct(discountPct);
      } catch (DiscountPctTooHighException e) {
         value = validator.getMaxDiscountPct();
      }
      sale.setDiscountPct(value);

      // Reapply down payment pct
      loanRequest.setDownPaymentPct(downPaymentPct);
   }

   @Override
   public void specifySellingPrice(String sellingPrice) throws ParseException {
      // Maintain current down payment pct
      double downPaymentPct = loanRequest.getDownPaymentPct();

      Sale sale = loanRequest.getSale();
      try {
         Money value = validateSellingPrice(sellingPrice);
         sale.setSellingPrice(value);
      } catch (DiscountPctTooHighException e) {
         sale.setDiscountPct(validator.getMaxDiscountPct());
      } catch (ValueRequiredException e) {
         // No-op
         return;
      }

      // Reapply down payment pct
      loanRequest.setDownPaymentPct(downPaymentPct);
   }

   @Override
   public void specifyDownPayment(String downPayment) throws ParseException {
      try {
         Money value = validateDownPayment(downPayment);
         loanRequest.setDownPayment(value);
      } catch (DownPaymentPctTooLowException e) {
         loanRequest.setDownPaymentPct(validator.getMinDownPaymentPct());
      } catch (ValueRequiredException e) {
         // No-op
         return;
      }
   }

   @Override
   public void specifyDownPaymentPct(String downPaymentPct) throws ParseException {
      try {
         double value = validateDownPaymentPct(downPaymentPct);
         loanRequest.setDownPaymentPct(value);
      } catch (DownPaymentPctTooLowException e) {
         loanRequest.setDownPaymentPct(validator.getMinDownPaymentPct());
      }
   }

   @Override
   public void specifyLoanAmount(String loanAmount) throws ParseException {
      try {
         Money value = validateLoanAmount(loanAmount);
         loanRequest.setLoanAmount(value);
      } catch (DownPaymentPctTooLowException e) {
         loanRequest.setDownPaymentPct(validator.getMinDownPaymentPct());
      } catch (ValueRequiredException e) {
         // No-op
         return;
      }
   }

   @Override
   public void specifyPreferredPayment(String prefPayment) throws ParseException {
      Money value = validatePreferredPayment(prefPayment);
      loanRequest.setPreferredPayment(value);
   }

   @Override
   public void specifyPreferredTerm(String prefTerm) throws ParseException {
      Integer value;
      try {
         value = validatePreferredTerm(prefTerm);
      } catch (TermTooLongException e) {
         value = validator.getMaxTermLength();
      }
      loanRequest.setPreferredTerm(value);
   }

   @Override
   public void submitLoanRequest(Consumer<Optional<LoanOffer>> resultConsumer,
                                 Consumer<Throwable> exceptionConsumer) {
      new SwingCommand<>(
              new SubmitLoanRequestCommand(loanRequest, facade.getIdentity()),
              resultConsumer::accept,
              exceptionConsumer::accept
      ).execute();
   }

   // Validation
   ///////////////

   @Override
   public Money validateDiscount(String discount) throws
           ParseException, DiscountPctTooHighException {
      discount = discount.trim();
      // Optional field, empty means 0
      if (discount.isEmpty())
         discount = "0";

      return validator.validateDiscount(
              discount, loanRequest.getSale().getBasePrice());
   }

   @Override
   public double validateDiscountPct(String discountPct) throws
           ParseException, DiscountPctTooHighException {
      discountPct = discountPct.trim();
      // Optional field, empty means 0
      if (discountPct.isEmpty())
         discountPct = "0";

      return validator.validateDiscountPct(discountPct);
   }

   @Override
   public Money validateSellingPrice(String sellingPrice) throws
           ParseException, DiscountPctTooHighException, ValueRequiredException {
      sellingPrice = sellingPrice.trim();
      // Required field
      if (sellingPrice.isEmpty())
         throw new ValueRequiredException("Selling price");

      return validator.validateSellingPrice(
              sellingPrice, loanRequest.getSale().getBasePrice());
   }

   @Override
   public Money validateDownPayment(String downPayment) throws
           ParseException, DownPaymentPctTooLowException, ValueRequiredException {
      downPayment = downPayment.trim();
      // Required field
      if (downPayment.isEmpty())
         throw new ValueRequiredException("Down payment");

      return validator.validateDownPayment(
              downPayment, loanRequest.getSale().getSellingPrice());
   }

   @Override
   public double validateDownPaymentPct(String downPaymentPct) throws
           ParseException, DownPaymentPctTooLowException {
      downPaymentPct = downPaymentPct.trim();
      // Optional field, empty means 0
      if (downPaymentPct.isEmpty())
         downPaymentPct = "0";

      return validator.validateDownPaymentPct(
              downPaymentPct, loanRequest.getSale().getSellingPrice());
   }

   @Override
   public Money validateLoanAmount(String loanAmount) throws
           ParseException, DownPaymentPctTooLowException, ValueRequiredException {
      loanAmount = loanAmount.trim();
      // Required field
      if (loanAmount.isEmpty())
         throw new ValueRequiredException("Loan amount");

      return validator.validateLoanAmount(
              loanAmount, loanRequest.getSale().getSellingPrice());
   }

   @Override
   public Money validatePreferredPayment(String prefPayment) throws ParseException {
      prefPayment = prefPayment.trim();
      // Optional field, empty means unspecified (null)
      if (prefPayment.isEmpty())
         return null;

      return validator.validatePreferredPayment(prefPayment);
   }

   @Override
   public Integer validatePreferredTerm(String prefTerm) throws
           ParseException, TermTooLongException {
      prefTerm = prefTerm.trim();
      // Optional field, empty means unspecified (null)
      if (prefTerm.isEmpty())
         return null;

      return validator.validatePreferredTerm(prefTerm);
   }
}
