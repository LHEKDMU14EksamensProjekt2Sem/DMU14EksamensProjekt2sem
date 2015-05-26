package logic.util.annuity;

import util.finance.Money;

import java.util.ArrayList;
import java.util.List;

public class AnnuityCalculator {
   public Payment computePayment(Money principal, double interest, int term, int period) {
      return computePayments(principal, interest, term, period).get(period - 1);
   }

   public List<Payment> computePayments(Money principal, double interest, int term) {
      return computePayments(principal, interest, term, term);
   }

   private List<Payment> computePayments(Money principal, double interest, int term, int period) {
      if (principal.doubleValue() < 0.01)
         throw new IllegalArgumentException("principal must be >= 0.01");

      if (interest <= 0)
         throw new IllegalArgumentException("interest must be > 0");

      if (interest == Double.POSITIVE_INFINITY)
         throw new IllegalArgumentException("interest cannot be Double.POSITIVE_INFINITY");

      if (Double.isNaN(interest))
         throw new IllegalArgumentException("interest cannot be Double.NaN");

      if (term <= 0)
         throw new IllegalArgumentException("term must be > 0)");

      if (period <= 0)
         throw new IllegalArgumentException("period must be > 0");

      if (period > term)
         throw new IllegalArgumentException("period must be <= term");

      /**
       * c = (r * P0) / (1 - (1 + r)^(-N))
       * https://en.wikipedia.org/wiki/Fixed-rate_mortgage
       */
      double r = (interest / 12);
      double partial = Math.pow(1 + r, -term);
      double denominator = (1 - partial);
      double c = (r * principal.doubleValue() / denominator);

      double balance = principal.doubleValue();
      List<Payment> payments = new ArrayList<>();

      for (int i = 0; i < period; i++) {
         double principalPaid = (c - balance * r);
         double interestPaid = (balance * r);

         payments.add(
                 new Payment(
                         new Money(balance),
                         new Money(c),
                         new Money(principalPaid),
                         new Money(interestPaid)));

         balance -= principalPaid;
      }

      return payments;
   }
}
