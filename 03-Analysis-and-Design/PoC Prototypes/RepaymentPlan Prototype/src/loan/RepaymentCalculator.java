package loan;

import java.math.BigDecimal;

public class RepaymentCalculator {
   public BigDecimal calculateMonthlyPayment(BigDecimal principal, double interestRate, int term) {
//      interestRate /= 12;
      BigDecimal res = new BigDecimal((principal.doubleValue() * interestRate) / (1 - Math.pow(1 + interestRate, -term)));
      res = res.setScale(2, BigDecimal.ROUND_HALF_UP);
      return res;
   }

   public int calculateTerm(BigDecimal principal, double interestRate, BigDecimal monthlyPayment) {
//      interestRate /= 12;
      return (int) Math.ceil(Math.log(1 / (1 - (principal.doubleValue() * interestRate) / monthlyPayment.doubleValue())) / Math.log(1 + interestRate));
   }
}
