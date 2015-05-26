package logic.util.calculator;

import com.ferrari.finances.dk.rki.Rating;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class InterestRateCalculatorTest {
   @Rule
   public ExpectedException thrown = ExpectedException.none();

   InterestRateCalculator calc;

   @Before
   public void setup() {
      calc = new InterestRateCalculator();
   }

   @Test // TC-17
   public void testZeroTermThrowsIllegalArgumentException() {
      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("Term must be > 0");

      double overnightRate = 0.01;
      Rating creditRating = Rating.A;
      double downPaymentPct = 0.50;
      int term = 0;

      double interestRate = calc.computeInterestRate(
              overnightRate, creditRating, downPaymentPct, term);
   }

   @Test // TC-18
   public void test100PctDownPaymentThrowsIllegalArgumentException() {
      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("Down payment pct. must be < 100%");

      double overnightRate = 0.01;
      Rating creditRating = Rating.A;
      double downPaymentPct = 1.00;
      int term = 37;

      double interestRate = calc.computeInterestRate(
              overnightRate, creditRating, downPaymentPct, term);
   }
}
