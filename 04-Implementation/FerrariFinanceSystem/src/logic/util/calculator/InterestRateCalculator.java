package logic.util.calculator;

import com.ferrari.finances.dk.rki.Rating;

public class InterestRateCalculator {
   double computeInterestRate(double overnightRate, Rating creditRating,
                              double downPaymentPct, int term) {
      if (term <= 0)
         throw new IllegalArgumentException("Term must be > 0");

      if (downPaymentPct < 0.20)
         throw new IllegalArgumentException("Down payment pct. must be >= 20%");

      if (downPaymentPct >= 1.00)
         throw new IllegalArgumentException("Down payment pct. must be < 100%");

      if (creditRating == Rating.D)
         throw new IllegalArgumentException("Credit rating must be A, B, or C");

      if (Double.isNaN(overnightRate))
         throw new IllegalArgumentException("Overnight rate cannot be Double.NaN");

      if (overnightRate == Double.POSITIVE_INFINITY)
         throw new IllegalArgumentException("Overnight rate cannot be Double.POSITIVE_INFINITY");

      if (overnightRate == Double.NEGATIVE_INFINITY)
         throw new IllegalArgumentException("Overnight rate cannot be Double.NEGATIVE_INFINITY");

      return 0;
   }
}
