package logic.session.requestloan.validation;

import exceptions.DiscountPctTooHighException;
import exceptions.DownPaymentPctTooLowException;
import exceptions.TermTooLongException;
import util.finance.Money;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

public class RequestDetailsValidatorImpl implements RequestDetailsValidator {
   private final static double
           MAX_DISCOUNT_PCT = 0.1,
           MIN_DOWN_PAYMENT_PCT = 0.2;

   private final static int
           MAX_TERM_LENGTH = 240;

   private NumberFormat moneyFormat, percentFormat;

   public RequestDetailsValidatorImpl(NumberFormat moneyFormat, NumberFormat percentFormat) {
      this.moneyFormat = moneyFormat;
      this.percentFormat = percentFormat;
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
   public Money validateDiscount(String discount, Money sellingPrice) throws
           ParseException, DiscountPctTooHighException {
      Money amount = new Money((BigDecimal) moneyFormat.parse(discount));
      double pct = (amount.doubleValue() / sellingPrice.doubleValue());
      if (pct > MAX_DISCOUNT_PCT)
         throw new DiscountPctTooHighException(pct);
      return amount;
   }

   @Override
   public double validateDiscountPct(String discountPct) throws
           ParseException, DiscountPctTooHighException {
      double pct = percentFormat.parse(discountPct).doubleValue() / 100;
      if (pct > MAX_DISCOUNT_PCT)
         throw new DiscountPctTooHighException(pct);
      return pct;
   }

   @Override
   public Money validateSellingPrice(String sellingPrice, Money basePrice) throws
           ParseException, DiscountPctTooHighException {
      Money amount = new Money((BigDecimal) moneyFormat.parse(sellingPrice));
      double pct = (amount.doubleValue() / basePrice.doubleValue());
      if (pct < 1 - MAX_DISCOUNT_PCT)
         throw new DiscountPctTooHighException(pct);
      return amount;
   }

   @Override
   public Money validateDownPayment(String downPayment, Money sellingPrice) throws
           ParseException, DownPaymentPctTooLowException {
      Money amount = new Money((BigDecimal) moneyFormat.parse(downPayment));
      double pct = (amount.doubleValue() / sellingPrice.doubleValue());
      if (pct < MIN_DOWN_PAYMENT_PCT)
         throw new DownPaymentPctTooLowException(pct);
      return amount;
   }

   @Override
   public double validateDownPaymentPct(String downPaymentPct, Money sellingPrice) throws
           ParseException, DownPaymentPctTooLowException {
      double pct = percentFormat.parse(downPaymentPct).doubleValue() / 100;
      if (pct < MIN_DOWN_PAYMENT_PCT)
         throw new DownPaymentPctTooLowException(pct);
      return pct;
   }

   @Override
   public Money validateLoanAmount(String loanAmount, Money sellingPrice) throws
           ParseException, DownPaymentPctTooLowException {
      Money amount = new Money((BigDecimal) moneyFormat.parse(loanAmount));
      double pct = (amount.doubleValue() / sellingPrice.doubleValue());
      if (pct > 1 - MIN_DOWN_PAYMENT_PCT)
         throw new DownPaymentPctTooLowException(pct);
      return amount;
   }

   @Override
   public Money validatePreferredRepayment(String prefRepayment) throws
           ParseException {
      return new Money((BigDecimal) moneyFormat.parse(prefRepayment));
   }

   @Override
   public int validatePreferredTerm(String prefTerm) throws
           ParseException, TermTooLongException {
      int term = Integer.parseInt(prefTerm);
      if (term > MAX_TERM_LENGTH)
         throw new TermTooLongException(term);
      return term;
   }
}
