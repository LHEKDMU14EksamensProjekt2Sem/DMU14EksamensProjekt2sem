package logic.util.calculator;

import com.ferrari.finances.dk.rki.Rating;

public class InterestRateCalculator {
   double computeInterestRate(double overnightRate, Rating creditRating,
                              double downPaymentPct, int term) {
      if (term <= 0)
         throw new IllegalArgumentException("Term must be > 0");

      return 0;
   }
}
