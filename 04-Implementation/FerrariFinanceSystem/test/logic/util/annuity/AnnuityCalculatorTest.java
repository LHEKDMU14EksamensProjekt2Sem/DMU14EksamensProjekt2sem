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

   private void doAsserts(Payment p,
                          Money expAmount,
                          Money expPrincipalPaid,
                          Money expInterestPaid,
                          Money expEndingBalance) {
      assertEquals("Amount", expAmount, p.getAmount());
      assertEquals("Principal paid", expPrincipalPaid, p.getPrincipalPaid());
      assertEquals("Interest paid", expInterestPaid, p.getInterestPaid());
      assertEquals("Ending balance", expEndingBalance, p.getEndingBalance());
   }

   @Test // TC-01
   public void test2TermPeriod1() {
      Money principal = new Money(1000);
      double interest = 0.10;
      int term = 2;
      int period = 1;

      Money expAmount = new Money(506.26);
      Money expPrincipalPaid = new Money(497.93);
      Money expInterestPaid = new Money(8.33);
      Money expEndingBalance = new Money(502.07);

      Payment p = calc.computePayment(principal, interest, term, period);
      doAsserts(p, expAmount, expPrincipalPaid, expInterestPaid, expEndingBalance);
   }

   @Test // TC-02
   public void test2TermPeriod2() {
      Money principal = new Money(1000);
      double interest = 0.10;
      int term = 2;
      int period = 2;

      Money expAmount = new Money(506.26);
      Money expPrincipalPaid = new Money(502.07);
      Money expInterestPaid = new Money(4.18);
      Money expEndingBalance = new Money(0);

      Payment p = calc.computePayment(principal, interest, term, period);
      doAsserts(p, expAmount, expPrincipalPaid, expInterestPaid, expEndingBalance);
   }

   @Test // TC-03
   public void testMinimumAmountPeriod1() {
      Money principal = new Money(0.01);
      double interest = 1;
      int term = 2;
      int period = 1;

      Money expAmount = new Money(0.01);
      Money expPrincipalPaid = new Money(0.00);
      Money expInterestPaid = new Money(0.00);
      Money expEndingBalance = new Money(0.01);

      Payment p = calc.computePayment(principal, interest, term, period);
      doAsserts(p, expAmount, expPrincipalPaid, expInterestPaid, expEndingBalance);
   }

   @Test // TC-04
   public void testMinimumAmountPeriod2() {
      Money principal = new Money(0.01);
      double interest = 1;
      int term = 2;
      int period = 2;

      Money expAmount = new Money(0.01);
      Money expPrincipalPaid = new Money(0.01);
      Money expInterestPaid = new Money(0);
      Money expEndingBalance = new Money(0);

      Payment p = calc.computePayment(principal, interest, term, period);
      doAsserts(p, expAmount, expPrincipalPaid, expInterestPaid, expEndingBalance);
   }

   @Test // TC-05
   public void testVerySmallInterestPeriod1() {
      Money principal = new Money(1000);
      double interest = 0.00001;
      int term = 2;
      int period = 1;

      Money expAmount = new Money(500);
      Money expPrincipalPaid = new Money(500);
      Money expInterestPaid = new Money(0);
      Money expEndingBalance = new Money(500);

      Payment p = calc.computePayment(principal, interest, term, period);
      doAsserts(p, expAmount, expPrincipalPaid, expInterestPaid, expEndingBalance);
   }

   @Test // TC-06
   public void testVerySmallInterestPeriod2() {
      Money principal = new Money(1000);
      double interest = 0.00001;
      int term = 2;
      int period = 2;

      Money expAmount = new Money(500);
      Money expPrincipalPaid = new Money(500);
      Money expInterestPaid = new Money(0);
      Money expEndingBalance = new Money(0);

      Payment p = calc.computePayment(principal, interest, term, period);
      doAsserts(p, expAmount, expPrincipalPaid, expInterestPaid, expEndingBalance);
   }

   @Test // TC-07
   public void testMinimumTerm() {
      Money principal = new Money(1000);
      double interest = 0.10;
      int term = 1;
      int period = 1;

      Money expAmount = new Money(1008.33);
      Money expPrincipalPaid = new Money(1000);
      Money expInterestPaid = new Money(8.33);
      Money expEndingBalance = new Money(0);

      Payment p = calc.computePayment(principal, interest, term, period);
      doAsserts(p, expAmount, expPrincipalPaid, expInterestPaid, expEndingBalance);
   }

   @Test // TC-08
   public void testPoint004PrincipalThrowsIllegalArgumentException() {
      Money principal = new Money(0.004);
      double interest = 0.10;
      int term = 2;
      int period = 1;

      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("principal must be >= 0.01");
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

   @Test // TC-15
   public void test10TermPeriod1() {
      Money principal = new Money(1000);
      double interest = 0.10;
      int term = 10;
      int period = 1;

      Money expAmount = new Money(104.64);
      Money expPrincipalPaid = new Money(96.31);
      Money expInterestPaid = new Money(8.33);
      Money expEndingBalance = new Money(903.69);

      Payment p = calc.computePayment(principal, interest, term, period);
      doAsserts(p, expAmount, expPrincipalPaid, expInterestPaid, expEndingBalance);
   }

   @Test // 16
   public void test10TermPeriod10() {
      Money principal = new Money(1000);
      double interest = 0.10;
      int term = 10;
      int period = 10;

      Money expAmount = new Money(104.64);
      Money expPrincipalPaid = new Money(103.78);
      Money expInterestPaid = new Money(0.86);
      Money expEndingBalance = new Money(0);

      Payment p = calc.computePayment(principal, interest, term, period);
      doAsserts(p, expAmount, expPrincipalPaid, expInterestPaid, expEndingBalance);
   }
}
