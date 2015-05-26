package logic.util.calculator;

import com.ferrari.finances.dk.rki.Rating;

public class InterestRateCalculator {
   double computeInterestRate(double overnightRate, Rating creditRating,
                              double downPaymentPct, int term) {
      if (term <= 0)
         throw new IllegalArgumentException("Term must be > 0");

      if (downPaymentPct >= 1.00)
         throw new IllegalArgumentException("Down payment pct. must be < 100%");

      return 0;
   }
}
