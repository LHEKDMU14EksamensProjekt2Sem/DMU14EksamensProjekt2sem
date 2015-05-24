package loan;

import java.math.BigDecimal;
import java.util.Date;

public class Repayment {
   private Date date;
   private BigDecimal payment, balance;
   private double interestRate;

   public Repayment(Date date, BigDecimal payment, BigDecimal balance, double interestRate) {
      this.date = date;
      this.payment = payment;
      this.balance = balance;
      this.interestRate = interestRate;
   }

   public Date getDate() {
      return date;
   }

   public BigDecimal getPayment() {
      return payment;
   }

   public void setPayment(BigDecimal payment) {
      this.payment = payment;
   }

   public BigDecimal getInterestPaid() {
      BigDecimal res = balance.multiply(new BigDecimal(interestRate));
      res = res.setScale(2, BigDecimal.ROUND_HALF_UP);
      return res;
   }

   public BigDecimal getPrincipalPaid() {
      return payment.subtract(getInterestPaid());
   }

   public BigDecimal getEndingBalance() {
      return balance.subtract(getPrincipalPaid());
   }
}
