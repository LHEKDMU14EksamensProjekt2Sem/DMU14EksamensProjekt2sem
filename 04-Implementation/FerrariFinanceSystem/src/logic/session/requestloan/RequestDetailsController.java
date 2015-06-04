package logic.session.requestloan;

import domain.Car;
import domain.CarModel;
import domain.LoanOffer;
import domain.LoanRequest;
import exceptions.DiscountPctTooHighException;
import exceptions.DownPaymentPctTooLowException;
import exceptions.TermTooLongException;
import exceptions.ValueRequiredException;
import util.finance.Money;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface RequestDetailsController {
   /**
    * Returns the current loan request
    *
    * @return the current loan request
    */
   LoanRequest getLoanRequest();

   /**
    * Initiates a fetch of all car models
    */
   void fetchCarModels(Consumer<List<CarModel>> resultConsumer,
                       Consumer<Throwable> exceptionConsumer);

   /**
    * Initiates a fetch of all cars belonging to <code>model</code>
    *
    * @param model a car model
    */
   void fetchCars(CarModel model,
                  Consumer<List<Car>> resultConsumer,
                  Consumer<Throwable> exceptionConsumer);

   void specifyCar(Car car);

   /**
    * Specifies a discount in DKK. If not directly specified,
    * the discount amount will be calculated based on any specified
    * discount percentage.
    *
    * @param discount a discount amount in DKK
    * @throws ParseException
    */
   void specifyDiscount(String discount) throws ParseException;

   /**
    * Specifies a discount percentage. If not directly specified,
    * the discount percentage will be calculated based on any specified
    * discount amount.
    *
    * @param discountPct a discount percentage
    * @throws ParseException
    */
   void specifyDiscountPct(String discountPct) throws ParseException;

   /**
    * Specifies the final selling price in DKK. If not directly specified,
    * the selling price will be calculated based on the base price and
    * any given discount.
    *
    * @param sellingPrice the final selling price in DKK
    * @throws ParseException
    */
   void specifySellingPrice(String sellingPrice) throws ParseException;

   /**
    * Specifies the down payment in DKK. If not directly specified, the
    * down payment will be calculated based on the selling price and any
    * specified loan amount.
    *
    * @param downPayment the down payment in DKK
    * @throws ParseException
    */
   void specifyDownPayment(String downPayment) throws ParseException;

   /**
    * Specifies the down payment percentage. If not directly specified, the
    * down payment percentage will be calculated based on any specified down
    * payment amount.
    *
    * @param downPaymentPct the down payment percentage
    * @throws ParseException
    */
   void specifyDownPaymentPct(String downPaymentPct) throws ParseException;

   /**
    * Specifies the loan amount in DKK. If not directly specified, the loan
    * amount will be calculated based on the selling price and any specified
    * down payment.
    *
    * @param loanAmount the loan amount in DKK
    * @throws ParseException
    */
   void specifyLoanAmount(String loanAmount) throws ParseException;

   /**
    * Specifies a preferred monthly payment in DKK. Must be specified unless
    * a preferred term is specified instead.
    *
    * @param prefPayment a preferred monthly repayment in DKK
    * @throws ParseException
    */
   void specifyPreferredPayment(String prefPayment) throws ParseException;

   /**
    * Specifies a preferred term given in number of months. Must be specified
    * unless a preferred monthly repayment is specified instead.
    *
    * @param prefTerm a preferred term given in months
    * @throws ParseException
    */
   void specifyPreferredTerm(String prefTerm) throws ParseException;

   /**
    * Submits the loan request for approval. If the request can be approved
    * immediately, a loan offer will be created. If a sales manager is required
    * to review the request before approval, it will be added to the list of
    * pending loan requests.
    */
   void submitLoanRequest(Consumer<Optional<LoanOffer>> resultConsumer,
                          Consumer<Throwable> exceptionConsumer);

   // Validation
   ///////////////

   Money validateDiscount(String discount) throws
           ParseException, DiscountPctTooHighException;

   double validateDiscountPct(String discountPct) throws
           ParseException, DiscountPctTooHighException;

   Money validateSellingPrice(String sellingPrice) throws
           ParseException, DiscountPctTooHighException, ValueRequiredException;

   Money validateDownPayment(String downPayment) throws
           ParseException, DownPaymentPctTooLowException, ValueRequiredException;

   double validateDownPaymentPct(String downPaymentPct) throws
           ParseException, DownPaymentPctTooLowException;

   Money validateLoanAmount(String loanAmount) throws
           ParseException, DownPaymentPctTooLowException, ValueRequiredException;

   Money validatePreferredPayment(String prefPayment) throws ParseException;

   Integer validatePreferredTerm(String prefTerm) throws
           ParseException, TermTooLongException;
}
