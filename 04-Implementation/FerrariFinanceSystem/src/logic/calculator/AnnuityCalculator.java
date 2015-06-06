package logic.calculator;

import util.finance.Money;

import java.util.ArrayList;
import java.util.List;

public class AnnuityCalculator {
   public Payment computePayment(Money principal, double interestRate, int term, int period) {
      return computePayments(principal, interestRate, term, period).get(period - 1);
   }

   public List<Payment> computePayments(Money principal, double interestRate, int term) {
      return computePayments(principal, interestRate, term, term);
   }

   private List<Payment> computePayments(Money principal, double interestRate, int term, int period) {
      if (principal == null)
         throw new NullPointerException("principal cannot be null");

      if (principal.doubleValue() < 0.01)
         throw new IllegalArgumentException("principal must be >= 0.01");

      if (interestRate <= 0)
         throw new IllegalArgumentException("interestRate must be > 0");

      if (interestRate == Double.POSITIVE_INFINITY)
         throw new IllegalArgumentException("interestRate cannot be Double.POSITIVE_INFINITY");

      if (Double.isNaN(interestRate))
         throw new IllegalArgumentException("interestRate cannot be Double.NaN");

      if (term <= 0)
         throw new IllegalArgumentException("term must be > 0)");

      if (period <= 0)
         throw new IllegalArgumentException("period must be > 0");

      if (period > term)
         throw new IllegalArgumentException("period must be <= term");

      // Convert yearly interest rate to monthly
      interestRate /= 12;

      double payment = computePayment(principal, interestRate, term);
      double balance = principal.doubleValue();
      List<Payment> payments = new ArrayList<>();

      for (int i = 0; i < period; i++) {
         double principalPaid = (payment - balance * interestRate);
         double interestPaid = (balance * interestRate);

         payments.add(
                 new AnnuityPayment(
                         new Money(balance),
                         new Money(payment),
                         new Money(principalPaid),
                         new Money(interestPaid)));

         balance -= principalPaid;
      }

      return payments;
   }

   /**
    * c = (r * P0) / (1 - (1 + r)^(-N))
    * https://en.wikipedia.org/wiki/Fixed-rate_mortgage
    */
   public double computePayment(Money principal, double interestRate, int term) {
      double partial = Math.pow(1 + interestRate, -term);
      double denominator = (1 - partial);
      return (interestRate * principal.doubleValue() / denominator);
   }

   /**
    * ln( (1 - PV(r)/P)^-1 ) / ln(1 + r)
    * http://www.financeformulas.net/Number-of-Periods-of-Annuity-from-Present-Value.html
    */
   public int computeTerm(Money principal, double interestRate, Money payment) {
      // Convert yearly interest rate to monthly
      interestRate /= 12;
      double partial = (principal.doubleValue() * interestRate / payment.doubleValue());
      double numerator = Math.log(1 / (1 - partial));
      double denominator = Math.log(1 + interestRate);
      return (int) Math.ceil(numerator / denominator);
   }
}
