package logic.calculator;

import com.ferrari.finances.dk.rki.Rating;

public class InterestRateCalculator {
   private static final double ONE_PCT = 0.01;

   public double computeInterestRate(double overnightRate, Rating creditRating,
                                     double downPaymentPct, int term) {
      if (creditRating == null)
         throw new NullPointerException("Credit rating cannot be null");

      if (term <= 0)
         throw new IllegalArgumentException("Term must be > 0");

      if (downPaymentPct < 0.20)
         throw new IllegalArgumentException("Down payment pct. must be >= 20%");

      if (downPaymentPct >= 1.00)
         throw new IllegalArgumentException("Down payment pct. must be < 100%");

      if (Double.isNaN(downPaymentPct))
         throw new IllegalArgumentException("Down payment pct. cannot be Double.NaN");

      if (Double.isNaN(overnightRate))
         throw new IllegalArgumentException("Overnight rate cannot be Double.NaN");

      if (overnightRate == Double.POSITIVE_INFINITY)
         throw new IllegalArgumentException("Overnight rate cannot be Double.POSITIVE_INFINITY");

      if (overnightRate == Double.NEGATIVE_INFINITY)
         throw new IllegalArgumentException("Overnight rate cannot be Double.NEGATIVE_INFINITY");

      double r = overnightRate;

      switch (creditRating) {
         case A:
            r += ONE_PCT;
            break;
         case B:
            r += 2 * ONE_PCT;
            break;
         case C:
            r += 3 * ONE_PCT;
            break;
         default:
            throw new IllegalArgumentException("Credit rating must be A, B, or C");
      }

      if (downPaymentPct < 0.50)
         r += ONE_PCT;

      if (term > 36)
         r += ONE_PCT;

      return r;
   }
}
