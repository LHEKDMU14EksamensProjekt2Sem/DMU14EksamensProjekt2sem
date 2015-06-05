package logic.session.createloanrequest.validation;

import exceptions.DiscountPctTooHighException;
import exceptions.DownPaymentPctTooLowException;
import exceptions.TermTooLongException;
import logic.format.GeneralNumberFormat;
import util.finance.Money;

import java.text.ParseException;

public class RequestDetailsValidatorImpl implements RequestDetailsValidator {
   private final static double
           MAX_DISCOUNT_PCT = 0.1,
           MIN_DOWN_PAYMENT_PCT = 0.2;

   private final static int
           MAX_TERM_LENGTH = 240; // 20 years

   private GeneralNumberFormat format;

   public RequestDetailsValidatorImpl(GeneralNumberFormat format) {
      this.format = format;
   }

   @Override
   public double getMinDownPaymentPct() {
      return MIN_DOWN_PAYMENT_PCT;
   }

   @Override
   public double getMaxDiscountPct() {
      return MAX_DISCOUNT_PCT;
   }

   @Override
   public int getMaxTermLength() {
      return MAX_TERM_LENGTH;
   }

   @Override
   public Money validateDiscount(String discount, Money basePrice) throws
           ParseException, DiscountPctTooHighException {
      Money amount = format.parseAmount(discount);
      double pct = (amount.doubleValue() / basePrice.doubleValue());
      if (pct > MAX_DISCOUNT_PCT)
         throw new DiscountPctTooHighException(pct);
      return amount;
   }

   @Override
   public double validateDiscountPct(String discountPct) throws
           ParseException, DiscountPctTooHighException {
      double pct = format.parsePercent(discountPct);
      if (pct > MAX_DISCOUNT_PCT)
         throw new DiscountPctTooHighException(pct);
      return pct;
   }

   @Override
   public Money validateSellingPrice(String sellingPrice, Money basePrice) throws
           ParseException, DiscountPctTooHighException {
      Money amount = format.parseAmount(sellingPrice);
      double pct = (amount.doubleValue() / basePrice.doubleValue());
      if (pct < 1 - MAX_DISCOUNT_PCT)
         throw new DiscountPctTooHighException(pct);
      return amount;
   }

   @Override
   public Money validateDownPayment(String downPayment, Money sellingPrice) throws
           ParseException, DownPaymentPctTooLowException {
      Money amount = format.parseAmount(downPayment);
      double pct = (amount.doubleValue() / sellingPrice.doubleValue());
      if (pct < MIN_DOWN_PAYMENT_PCT)
         throw new DownPaymentPctTooLowException(pct);
      return amount;
   }

   @Override
   public double validateDownPaymentPct(String downPaymentPct, Money sellingPrice) throws
           ParseException, DownPaymentPctTooLowException {
      double pct = format.parsePercent(downPaymentPct);
      if (pct < MIN_DOWN_PAYMENT_PCT)
         throw new DownPaymentPctTooLowException(pct);
      return pct;
   }

   @Override
   public Money validateLoanAmount(String loanAmount, Money sellingPrice) throws
           ParseException, DownPaymentPctTooLowException {
      Money amount = format.parseAmount(loanAmount);
      double pct = (amount.doubleValue() / sellingPrice.doubleValue());
      if (pct > 1 - MIN_DOWN_PAYMENT_PCT)
         throw new DownPaymentPctTooLowException(pct);
      return amount;
   }

   @Override
   public Money validatePreferredPayment(String prefPayment) throws ParseException {
      return format.parseAmount(prefPayment);
   }

   @Override
   public int validatePreferredTerm(String prefTerm) throws
           ParseException, TermTooLongException {
      int term = format.parseInteger(prefTerm);
      if (term > MAX_TERM_LENGTH)
         throw new TermTooLongException(term);
      return term;
   }
}
