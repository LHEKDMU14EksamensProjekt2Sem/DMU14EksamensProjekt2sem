package logic.util.annuity;

import util.finance.Money;

import java.math.RoundingMode;

public class AnnuityCalculator {
   public Payment computePayment(Money principal, double interest, int term, int period) {
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

      Money balance = null;
      Money endingBalance = principal;
      Money amount = new Money(c);
      Money principalPaid = null;
      Money interestPaid = null;
      for (int i = 0; i < period; i++) {
         balance = endingBalance;
         interestPaid = new Money(endingBalance.doubleValue() * r, RoundingMode.HALF_UP);
         principalPaid = new Money(c - endingBalance.doubleValue() * r, RoundingMode.HALF_UP);
         endingBalance = endingBalance.subtract(principalPaid);
      }

      return new Payment(balance, amount, principalPaid, interestPaid);
   }
}
