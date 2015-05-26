package logic.util.annuity;

import util.finance.Money;

public class AnnuityCalculator {
   public Payment computePayment(Money principal, double interest, int term, int period) {
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

//   double I, partial1, denominator, answer;
//
//   numPeriods *= 12;        //get number of months
//   if (rate > 0.01) {
//      I = rate / 100.0 / 12.0;         //get monthly rate from annual
//      partial1 = Math.pow((1 + I), (0.0 - numPeriods));
//      denominator = (1 - partial1) / I;
//   } else { //rate ~= 0
//      denominator = numPeriods;
//   }
//
//   answer = (-1 * loanAmt) / denominator;


      /**
       * c = (r * P0) / (1 - (1 + r)^(-N))
       * https://en.wikipedia.org/wiki/Fixed-rate_mortgage
       */
      double r = (interest / 12);
      double partial = Math.pow(1 + r, -term);
      double demoninator = (1 - partial);
      double c = (r * principal.doubleValue() / demoninator);

      Money amount = new Money(c);
      Money interestPaid = new Money(principal.doubleValue() * r);

      return new Payment(principal, amount, interestPaid);
   }
}
