package logic.session.requestloan;

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
import logic.command.FetchCarModelsCommand;
import logic.command.FetchCarsCommand;
import logic.command.SubmitLoanRequestCommand;
import logic.session.requestloan.validation.RequestDetailsValidator;
import logic.session.requestloan.validation.RequestDetailsValidatorImpl;
import util.finance.Money;
import util.swing.SwingCommand;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class RequestDetailsControllerImpl implements RequestDetailsController {
   private final RequestLoanFacade facade;
   private final RequestDetailsValidator validator;
   private final LoanRequest loanRequest;
   private final Sale sale;

   public RequestDetailsControllerImpl(RequestLoanFacade facade) {
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
      loanRequest.setDate(new Date(System.currentTimeMillis()));
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
      if (discount.trim().isEmpty()) {
         sale.setDiscount(Money.ZERO);
      } else {
         try {
            Money value = validateDiscount(discount);
            sale.setDiscount(value);
         } catch (DiscountPctTooHighException e) {
            sale.setDiscountPct(validator.getMaxDiscountPct());
         }
      }

      // Reapply down payment pct
      loanRequest.setDownPaymentPct(downPaymentPct);
   }

   @Override
   public void specifyDiscountPct(String discountPct) throws ParseException {
      // Maintain current down payment pct
      double downPaymentPct = loanRequest.getDownPaymentPct();

      Sale sale = loanRequest.getSale();
      if (discountPct.trim().isEmpty()) {
         sale.setDiscount(Money.ZERO);
      } else {
         double value;
         try {
            value = validateDiscountPct(discountPct);
         } catch (DiscountPctTooHighException e) {
            value = validator.getMaxDiscountPct();
         }
         sale.setDiscountPct(value);
      }

      // Reapply down payment pct
      loanRequest.setDownPaymentPct(downPaymentPct);
   }

   @Override
   public void specifySellingPrice(String sellingPrice) throws ParseException {
      // Maintain current down payment pct
      double downPaymentPct = loanRequest.getDownPaymentPct();

      Sale sale = loanRequest.getSale();
      if (!sellingPrice.trim().isEmpty()) {
         try {
            Money value = validateSellingPrice(sellingPrice);
            sale.setSellingPrice(value);
         } catch (DiscountPctTooHighException e) {
            sale.setDiscountPct(validator.getMaxDiscountPct());
         }
      }

      // Reapply down payment pct
      loanRequest.setDownPaymentPct(downPaymentPct);
   }

   @Override
   public void specifyDownPayment(String downPayment) throws ParseException {
      if (!downPayment.trim().isEmpty()) {
         try {
            Money value = validateDownPayment(downPayment);
            loanRequest.setDownPayment(value);
         } catch (DownPaymentPctTooLowException e) {
            loanRequest.setDownPaymentPct(validator.getMinDownPaymentPct());
         }
      }
   }

   @Override
   public void specifyDownPaymentPct(String downPaymentPct) throws ParseException {
      if (!downPaymentPct.trim().isEmpty()) {
         try {
            double value = validateDownPaymentPct(downPaymentPct);
            loanRequest.setDownPaymentPct(value);
         } catch (DownPaymentPctTooLowException e) {
            loanRequest.setDownPaymentPct(validator.getMinDownPaymentPct());
         }
      }
   }

   @Override
   public void specifyLoanAmount(String loanAmount) throws ParseException {
      if (!loanAmount.trim().isEmpty()) {
         try {
            Money value = validateLoanAmount(loanAmount);
            loanRequest.setLoanAmount(value);
         } catch (DownPaymentPctTooLowException e) {
            loanRequest.setDownPaymentPct(validator.getMinDownPaymentPct());
         }
      }
   }

   @Override
   public void specifyPreferredPayment(String prefPayment) throws ParseException {
      Money value = null;
      if (!prefPayment.trim().isEmpty()) {
         value = validatePreferredPayment(prefPayment);
      }
      loanRequest.setPreferredPayment(value);
   }

   @Override
   public void specifyPreferredTerm(String prefTerm) throws ParseException {
      Integer value = null;
      if (!prefTerm.trim().isEmpty()) {
         try {
            value = validatePreferredTerm(prefTerm);
         } catch (TermTooLongException e) {
            value = validator.getMaxTermLength();
         }
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
      return validator.validateDiscount(
              discount.trim(), loanRequest.getSale().getBasePrice());
   }

   @Override
   public double validateDiscountPct(String discountPct) throws
           ParseException, DiscountPctTooHighException {
      return validator.validateDiscountPct(
              discountPct.trim());
   }

   @Override
   public Money validateSellingPrice(String sellingPrice) throws
           ParseException, DiscountPctTooHighException {
      return validator.validateSellingPrice(
              sellingPrice.trim(), loanRequest.getSale().getBasePrice());
   }

   @Override
   public Money validateDownPayment(String downPayment) throws
           ParseException, DownPaymentPctTooLowException {
      return validator.validateDownPayment(
              downPayment.trim(), loanRequest.getSale().getSellingPrice());
   }

   @Override
   public double validateDownPaymentPct(String downPaymentPct) throws
           ParseException, DownPaymentPctTooLowException {
      return validator.validateDownPaymentPct(
              downPaymentPct.trim(), loanRequest.getSale().getSellingPrice());
   }

   @Override
   public Money validateLoanAmount(String loanAmount) throws
           ParseException, DownPaymentPctTooLowException {
      return validator.validateLoanAmount(
              loanAmount.trim(), loanRequest.getSale().getSellingPrice());
   }

   @Override
   public Money validatePreferredPayment(String prefPayment) throws ParseException {
      return validator.validatePreferredPayment(
              prefPayment.trim());
   }

   @Override
   public int validatePreferredTerm(String prefTerm) throws
           ParseException, TermTooLongException {
      return validator.validatePreferredTerm(
              prefTerm.trim());
   }
}
