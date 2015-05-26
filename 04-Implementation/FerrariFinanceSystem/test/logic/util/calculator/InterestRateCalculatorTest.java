package logic.util.calculator;

import com.ferrari.finances.dk.rki.Rating;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class InterestRateCalculatorTest {
   final double DELTA = 0.00001;

   @Rule
   public ExpectedException thrown = ExpectedException.none();

   InterestRateCalculator calc;

   @Before
   public void setup() {
      calc = new InterestRateCalculator();
   }

   @Test // TC-01
   public void testOvernight1PctCreditADown50PctTerm37Gives3PctRate() {
      double overnightRate = 0.01;
      Rating creditRating = Rating.A;
      double downPaymentPct = 0.50;
      int term = 37;

      double expected = 0.03;
      double actual = calc.computeInterestRate(
              overnightRate, creditRating, downPaymentPct, term);
      assertEquals(expected, actual, DELTA);
   }

   @Test // TC-02
   public void testOvernight1PctCreditADown50PctTerm36Gives2PctRate() {
      double overnightRate = 0.01;
      Rating creditRating = Rating.A;
      double downPaymentPct = 0.50;
      int term = 36;

      double expected = 0.02;
      double actual = calc.computeInterestRate(
              overnightRate, creditRating, downPaymentPct, term);
      assertEquals(expected, actual, DELTA);
   }

   @Test // TC-03
   public void testOvernight1PctCreditADown20PctTerm37Gives4PctRate() {
      double overnightRate = 0.01;
      Rating creditRating = Rating.A;
      double downPaymentPct = 0.20;
      int term = 37;

      double expected = 0.04;
      double actual = calc.computeInterestRate(
              overnightRate, creditRating, downPaymentPct, term);
      assertEquals(expected, actual, DELTA);
   }

   @Test // TC-04
   public void testOvernight1PctCreditADown20PctTerm36Gives3PctRate() {
      double overnightRate = 0.01;
      Rating creditRating = Rating.A;
      double downPaymentPct = 0.20;
      int term = 36;

      double expected = 0.03;
      double actual = calc.computeInterestRate(
              overnightRate, creditRating, downPaymentPct, term);
      assertEquals(expected, actual, DELTA);
   }

   @Test // TC-16
   public void testDownPaymentPctBelow20ThrowsIllegalArgumentException() {
      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("Down payment pct. must be >= 20%");

      double overnightRate = 0.01;
      Rating creditRating = Rating.A;
      double downPaymentPct = 0.1999;
      int term = 37;

      calc.computeInterestRate(
              overnightRate, creditRating, downPaymentPct, term);
   }

   @Test // TC-17
   public void testZeroTermThrowsIllegalArgumentException() {
      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("Term must be > 0");

      double overnightRate = 0.01;
      Rating creditRating = Rating.A;
      double downPaymentPct = 0.50;
      int term = 0;

      calc.computeInterestRate(
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

      calc.computeInterestRate(
              overnightRate, creditRating, downPaymentPct, term);
   }

   @Test // TC-19
   public void testCreditRatingDThrowsIllegalArgumentException() {
      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("Credit rating must be A, B, or C");

      double overnightRate = 0.01;
      Rating creditRating = Rating.D;
      double downPaymentPct = 0.50;
      int term = 37;

      calc.computeInterestRate(
              overnightRate, creditRating, downPaymentPct, term);
   }

   @Test // TC-20
   public void testNaNOvernightRateThrowsIllegalArgumentException() {
      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("Overnight rate cannot be Double.NaN");

      double overnightRate = Double.NaN;
      Rating creditRating = Rating.A;
      double downPaymentPct = 0.50;
      int term = 37;

      calc.computeInterestRate(
              overnightRate, creditRating, downPaymentPct, term);
   }

   @Test // TC-21
   public void testPosInftyOvernightRateThrowsIllegalArgumentException() {
      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("Overnight rate cannot be Double.POSITIVE_INFINITY");

      double overnightRate = Double.POSITIVE_INFINITY;
      Rating creditRating = Rating.A;
      double downPaymentPct = 0.50;
      int term = 37;

      calc.computeInterestRate(
              overnightRate, creditRating, downPaymentPct, term);
   }

   @Test // TC-22
   public void testNegInftyOvernightRateThrowsIllegalArgumentException() {
      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("Overnight rate cannot be Double.NEGATIVE_INFINITY");

      double overnightRate = Double.NEGATIVE_INFINITY;
      Rating creditRating = Rating.A;
      double downPaymentPct = 0.50;
      int term = 37;

      calc.computeInterestRate(
              overnightRate, creditRating, downPaymentPct, term);
   }
}
