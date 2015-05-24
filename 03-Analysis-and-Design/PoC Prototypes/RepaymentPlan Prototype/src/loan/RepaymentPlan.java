package loan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RepaymentPlan {
   private RepaymentCalculator calc;
   private BigDecimal principal;
   private double interestRate;
   private int term;
   private Calendar cal;

   public RepaymentPlan(RepaymentCalculator calc,
                        BigDecimal principal,
                        double interestRate,
                        BigDecimal monthlyPayment,
                        Calendar cal) {
      this(calc, principal, interestRate,
              calc.calculateTerm(principal, interestRate, monthlyPayment), cal);
   }

   public RepaymentPlan(RepaymentCalculator calc,
                        BigDecimal principal,
                        double interestRate,
                        int term,
                        Calendar cal) {
      this.calc = calc;
      this.principal = principal;
      this.interestRate = interestRate;
      this.term = term;
      this.cal = cal;
   }

   public BigDecimal getPrincipal() {
      return principal;
   }

   public BigDecimal getTotalAmountPayable() {
      BigDecimal res = calc.calculateMonthlyPayment(principal, interestRate, term);
      res = res.multiply(new BigDecimal(term));
      return res;
   }

   public BigDecimal getTotalCharge() {
      return getTotalAmountPayable().subtract(principal);
   }

   public int getTerm() {
      return term;
   }

   public double getInterestRate() {
      return interestRate;
   }

   public double getEffectiveAPR() {
      return getTotalCharge().doubleValue() / principal.doubleValue() / term * 12;
   }

   public List<Repayment> getRepayments() {
      BigDecimal balance = principal;
      BigDecimal payment = calc.calculateMonthlyPayment(principal, interestRate, term);
//      payment = payment.setScale(0, BigDecimal.ROUND_HALF_UP).setScale(2);

      List<Repayment> res = new ArrayList<>();
      Repayment r = null;
      for (int i = 0; i < term; i++) {
         r = new Repayment(cal.getTime(), payment, balance, interestRate);
         res.add(r);
         balance = r.getEndingBalance();
         cal.add(Calendar.MONTH, 1);
      }

      // Adjust final payment
      if (r != null)
         r.setPayment(r.getPayment().add(r.getEndingBalance()));

      return res;
   }
}
