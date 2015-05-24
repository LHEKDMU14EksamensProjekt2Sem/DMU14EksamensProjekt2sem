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
import logic.command.FetchCarModelsCommand;
import logic.command.FetchCarsCommand;
import logic.command.SubmitLoanRequestCommand;
import logic.session.requestloan.validation.RequestDetailsValidator;
import logic.session.requestloan.validation.RequestDetailsValidatorImpl;
import util.command.SwingCommand;
import util.finance.Money;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class RequestDetailsControllerImpl implements RequestDetailsController {
   private final RequestLoanFacade facade;
   private final RequestDetailsValidator validator;
   private final LoanRequest loanRequest;
   private final Sale sale;

   private NumberFormat moneyFormat, percentFormat;

   public RequestDetailsControllerImpl(RequestLoanFacade facade) {
      this.facade = facade;

      moneyFormat = NumberFormat.getNumberInstance();
      moneyFormat.setMinimumFractionDigits(2);
      moneyFormat.setMaximumFractionDigits(2);
      if (moneyFormat instanceof DecimalFormat) {
         DecimalFormat df = ((DecimalFormat) moneyFormat);
         df.setParseBigDecimal(true);
         df.setDecimalSeparatorAlwaysShown(true);
      }

      percentFormat = NumberFormat.getNumberInstance();
      percentFormat.setMinimumFractionDigits(2);
      percentFormat.setMaximumFractionDigits(2);

      validator = new RequestDetailsValidatorImpl(moneyFormat, percentFormat);

      Employee employee = facade.getUser().getEntity();

      sale = new Sale();
      sale.setSeller(employee);
      sale.setCustomer(facade.getCustomer());
      loanRequest = new LoanRequest();
      loanRequest.setSale(sale);
      loanRequest.setStatus(LoanRequestStatus.PENDING);
      loanRequest.setStatusByEmployee(employee);
      loanRequest.setDate(new Date(System.currentTimeMillis()));
   }

   @Override
   public NumberFormat getMoneyFormat() {
      return moneyFormat;
   }

   @Override
   public NumberFormat getPercentFormat() {
      return percentFormat;
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
      sale.setCar(car);
      loanRequest.setDownPaymentPct(validator.getMinDownPaymentPct());
   }

   @Override
   public void specifyDiscount(String discount) throws
           ParseException {
      // Maintain current down payment pct
      double downPaymentPct = loanRequest.getDownPaymentPct();
      Sale sale = loanRequest.getSale();
      String s = discount.trim();
      if (s.isEmpty()) {
         sale.setDiscount(Money.ZERO);
      } else {
         try {
            sale.setDiscount(
                    validator.validateDiscount(s, sale.getSellingPrice()));
         } catch (DiscountPctTooHighException e) {
            sale.setDiscountPct(validator.getMaxDiscountPct());
         }
      }
      loanRequest.setDownPaymentPct(downPaymentPct);
   }

   @Override
   public void specifyDiscountPct(String discountPct) throws
           ParseException {
      // Maintain current down payment pct
      double downPaymentPct = loanRequest.getDownPaymentPct();
      Sale sale = loanRequest.getSale();
      String s = discountPct.trim();
      if (s.isEmpty()) {
         sale.setDiscount(Money.ZERO);
      } else {
         try {
            sale.setDiscountPct(
                    validator.validateDiscountPct(s));
         } catch (DiscountPctTooHighException e) {
            sale.setDiscountPct(validator.getMaxDiscountPct());
         }
      }
      loanRequest.setDownPaymentPct(downPaymentPct);
   }

   @Override
   public void specifySellingPrice(String sellingPrice) throws
           ParseException {
      // Maintain current down payment pct
      double downPaymentPct = loanRequest.getDownPaymentPct();
      Sale sale = loanRequest.getSale();
      String s = sellingPrice.trim();
      if (!s.isEmpty()) {
         try {
            sale.setSellingPrice(
                    validator.validateSellingPrice(s, sale.getBasePrice()));
         } catch (DiscountPctTooHighException e) {
            sale.setDiscountPct(validator.getMaxDiscountPct());
         }
      }
      loanRequest.setDownPaymentPct(downPaymentPct);
   }

   @Override
   public void specifyDownPayment(String downPayment) throws
           ParseException {
      String s = downPayment.trim();
      if (!s.isEmpty()) {
         try {
            loanRequest.setDownPayment(
                    validator.validateDownPayment(s, loanRequest.getSale().getSellingPrice()));
         } catch (DownPaymentPctTooLowException e) {
            loanRequest.setDownPaymentPct(validator.getMinDownPaymentPct());
         }
      }
   }

   @Override
   public void specifyDownPaymentPct(String downPaymentPct) throws
           ParseException {
      String s = downPaymentPct.trim();
      if (!s.isEmpty()) {
         try {
            loanRequest.setDownPaymentPct(
                    validator.validateDownPaymentPct(s, loanRequest.getSale().getSellingPrice()));
         } catch (DownPaymentPctTooLowException e) {
            loanRequest.setDownPaymentPct(validator.getMinDownPaymentPct());
         }
      }
   }

   @Override
   public void specifyLoanAmount(String loanAmount) throws
           ParseException {
      String s = loanAmount.trim();
      if (!s.isEmpty()) {
         try {
            loanRequest.setLoanAmount(
                    validator.validateLoanAmount(s, loanRequest.getSale().getSellingPrice()));
         } catch (DownPaymentPctTooLowException e) {
            loanRequest.setDownPaymentPct(validator.getMinDownPaymentPct());
         }
      }
   }

   @Override
   public void specifyPreferredRepayment(String prefRepayment) throws
           ParseException {

   }

   @Override
   public void specifyPreferredTerm(String prefTerm) throws
           ParseException {

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
}
