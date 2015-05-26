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
      if (principal.doubleValue() < 0.10)
         throw new IllegalArgumentException("principal must be >= 0.10");

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
      double demoninator = (1 - partial);
      double c = (r * principal.doubleValue() / demoninator);

      Money balance = principal;
      Money amount = new Money(c);
      List<Payment> payments = new ArrayList<>();

      for (int i = 0; i < period; i++) {
         Money principalPaid = new Money(c - balance.doubleValue() * r);
         Money interestPaid = new Money(balance.doubleValue() * r);

         payments.add(
                 new Payment(balance, amount, principalPaid, interestPaid));

         balance = balance.subtract(principalPaid);
      }

      return payments;
   }
}
