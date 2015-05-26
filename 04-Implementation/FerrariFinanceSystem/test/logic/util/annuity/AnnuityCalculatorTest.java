package logic.util.annuity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import util.finance.Money;

import static org.junit.Assert.*;

public class AnnuityCalculatorTest {
   @Rule
   public ExpectedException thrown = ExpectedException.none();
   AnnuityCalculator calc;

   @Before
   public void setup() {
      calc = new AnnuityCalculator();
   }

   @Test // TC-01
   public void testDefaultPaymentPeriod1() {
      Money principal = new Money(1000);
      double interest = 0.10;
      int term = 2;
      int period = 1;

      Money expectedAmount = new Money(506.26);
      Money expectedPrincipalPaid = new Money(497.93);
      Money expectedInterestPaid = new Money(8.33);
      Money expectedEndingBalance = new Money(502.07);

      Payment p = calc.computePayment(principal, interest, term, period);
      assertEquals("Amount", expectedAmount, p.getAmount());
      assertEquals("Principal paid", expectedPrincipalPaid, p.getPrincipalPaid());
      assertEquals("Interest paid", expectedInterestPaid, p.getInterestPaid());
      assertEquals("Ending balance", expectedEndingBalance, p.getEndingBalance());
   }

   @Test // TC-02
   public void testDefaultPaymentPeriod2() {
      Money principal = new Money(1000);
      double interest = 0.10;
      int term = 2;
      int period = 2;

      Money expectedAmount = new Money(506.26);
      Money expectedPrincipalPaid = new Money(502.07);
      Money expectedInterestPaid = new Money(4.18);
      Money expectedEndingBalance = new Money(0);

      Payment p = calc.computePayment(principal, interest, term, period);
      assertEquals("Amount", expectedAmount, p.getAmount());
      assertEquals("Principal paid", expectedPrincipalPaid, p.getPrincipalPaid());
      assertEquals("Interest paid", expectedInterestPaid, p.getInterestPaid());
      assertEquals("Ending balance", expectedEndingBalance, p.getEndingBalance());
   }

   @Test // TC-03
   public void testMinimumAmountPeriod1() {
      Money principal = new Money(0.10);
      double interest = 1;
      int term = 2;
      int period = 1;

      Money expectedAmount = new Money(0.06);
      Money expectedPrincipalPaid = new Money(0.05);
      Money expectedInterestPaid = new Money(0.01);
      Money expectedEndingBalance = new Money(0.05);

      Payment p = calc.computePayment(principal, interest, term, period);
      assertEquals("Amount", expectedAmount, p.getAmount());
      assertEquals("Principal paid", expectedPrincipalPaid, p.getPrincipalPaid());
      assertEquals("Interest paid", expectedInterestPaid, p.getInterestPaid());
      assertEquals("Ending balance", expectedEndingBalance, p.getEndingBalance());
   }

   @Test // TC-04
   public void testMinimumAmountPeriod2() {
      Money principal = new Money(0.10);
      double interest = 1;
      int term = 2;
      int period = 2;

      Money expectedAmount = new Money(0.06);
      Money expectedPrincipalPaid = new Money(0.05);
      Money expectedInterestPaid = new Money(0);
      Money expectedEndingBalance = new Money(0);

      Payment p = calc.computePayment(principal, interest, term, period);
      assertEquals("Amount", expectedAmount, p.getAmount());
      assertEquals("Principal paid", expectedPrincipalPaid, p.getPrincipalPaid());
      assertEquals("Interest paid", expectedInterestPaid, p.getInterestPaid());
      assertEquals("Ending balance", expectedEndingBalance, p.getEndingBalance());
   }

   @Test // TC-05
   public void testVerySmallInterestPeriod1() {
      Money principal = new Money(1000);
      double interest = 0.00001;
      int term = 2;
      int period = 1;

      Money expectedAmount = new Money(500);
      Money expectedPrincipalPaid = new Money(500);
      Money expectedInterestPaid = new Money(0);
      Money expectedEndingBalance = new Money(500);

      Payment p = calc.computePayment(principal, interest, term, period);
      assertEquals("Amount", expectedAmount, p.getAmount());
      assertEquals("Principal paid", expectedPrincipalPaid, p.getPrincipalPaid());
      assertEquals("Interest paid", expectedInterestPaid, p.getInterestPaid());
      assertEquals("Ending balance", expectedEndingBalance, p.getEndingBalance());
   }

   @Test // TC-06
   public void testVerySmallInterestPeriod2() {
      Money principal = new Money(1000);
      double interest = 0.00001;
      int term = 2;
      int period = 2;

      Money expectedAmount = new Money(500);
      Money expectedPrincipalPaid = new Money(500);
      Money expectedInterestPaid = new Money(0);
      Money expectedEndingBalance = new Money(0);

      Payment p = calc.computePayment(principal, interest, term, period);
      assertEquals("Amount", expectedAmount, p.getAmount());
      assertEquals("Principal paid", expectedPrincipalPaid, p.getPrincipalPaid());
      assertEquals("Interest paid", expectedInterestPaid, p.getInterestPaid());
      assertEquals("Ending balance", expectedEndingBalance, p.getEndingBalance());
   }

   @Test // TC-07
   public void testMinimumTerm() {
      Money principal = new Money(1000);
      double interest = 0.10;
      int term = 1;
      int period = 1;

      Money expectedAmount = new Money(1008.33);
      Money expectedPrincipalPaid = new Money(1000);
      Money expectedInterestPaid = new Money(8.33);
      Money expectedEndingBalance = new Money(0);

      Payment p = calc.computePayment(principal, interest, term, period);
      assertEquals("Amount", expectedAmount, p.getAmount());
      assertEquals("Principal paid", expectedPrincipalPaid, p.getPrincipalPaid());
      assertEquals("Interest paid", expectedInterestPaid, p.getInterestPaid());
      assertEquals("Ending balance", expectedEndingBalance, p.getEndingBalance());
   }

   @Test // TC-08
   public void testPoint0949PrincipalThrowsIllegalArgumentException() {
      Money principal = new Money(0.0949);
      double interest = 0.10;
      int term = 2;
      int period = 1;

      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("principal must be >= 0.10");
      calc.computePayment(principal, interest, term, period);
   }

   @Test // TC-09
   public void testZeroInterestThrowsIllegalArgumentException() {
      Money principal = new Money(1000);
      double interest = 0;
      int term = 2;
      int period = 1;

      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("interest must be > 0");
      calc.computePayment(principal, interest, term, period);
   }

   @Test // TC-10
   public void testZeroTermsThrowsIllegalArgumentException() {
      Money principal = new Money(1000);
      double interest = 0.10;
      int term = 0;
      int period = 1;

      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("term must be > 0");
      calc.computePayment(principal, interest, term, period);
   }

   @Test // TC-11
   public void testPeriodZeroThrowsIllegalArgumentException() {
      Money principal = new Money(1000);
      double interest = 0.10;
      int term = 2;
      int period = 0;

      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("period must be > 0");
      calc.computePayment(principal, interest, term, period);
   }

   @Test // TC-12
   public void testInterestPositiveInfinityThrowsIllegalArgumentException() {
      Money principal = new Money(1000);
      double interest = Double.POSITIVE_INFINITY;
      int term = 2;
      int period = 1;

      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("interest cannot be Double.POSITIVE_INFINITY");
      calc.computePayment(principal, interest, term, period);
   }

   @Test // TC-13
   public void testInterestNaNThrowsIllegalArgumentException() {
      Money principal = new Money(1000);
      double interest = Double.NaN;
      int term = 2;
      int period = 1;

      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("interest cannot be Double.NaN");
      calc.computePayment(principal, interest, term, period);
   }

   @Test // TC-14
   public void testPeriodGreaterThanTermThrowsIllegalArgumentException() {
      Money principal = new Money(1000);
      double interest = 0.10;
      int term = 1;
      int period = 2;

      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("period must be <= term");
      calc.computePayment(principal, interest, term, period);
   }
}
