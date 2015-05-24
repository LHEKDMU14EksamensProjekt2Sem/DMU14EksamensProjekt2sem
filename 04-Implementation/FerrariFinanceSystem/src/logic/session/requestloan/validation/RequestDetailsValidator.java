package logic.session.requestloan.validation;

import exceptions.DiscountPctTooHighException;
import exceptions.DownPaymentPctTooLowException;
import exceptions.TermTooLongException;
import util.finance.Money;

import java.text.ParseException;

public interface RequestDetailsValidator {
   double getMinDownPaymentPct();

   double getMaxDiscountPct();

   int getMaxTermLength();

   Money validateDiscount(String discount, Money sellingPrice) throws
           ParseException, DiscountPctTooHighException;

   double validateDiscountPct(String discountPct) throws
           ParseException, DiscountPctTooHighException;

   Money validateSellingPrice(String sellingPrice, Money basePrice) throws
           ParseException, DiscountPctTooHighException;

   Money validateDownPayment(String downPayment, Money sellingPrice) throws
           ParseException, DownPaymentPctTooLowException;

   double validateDownPaymentPct(String downPaymentPct, Money sellingPrice) throws
           ParseException, DownPaymentPctTooLowException;

   Money validateLoanAmount(String loanAmount, Money sellingPrice) throws
           ParseException, DownPaymentPctTooLowException;

   Money validatePreferredRepayment(String prefRepayment) throws
           ParseException;

   int validatePreferredTerm(String prefTerm) throws
           ParseException, TermTooLongException;
}
