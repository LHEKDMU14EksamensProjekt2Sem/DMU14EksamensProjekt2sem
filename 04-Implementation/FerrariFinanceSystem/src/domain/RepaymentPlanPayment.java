package domain;

import util.finance.Money;

import java.time.LocalDate;

public class RepaymentPlanPayment {
   private LocalDate date;
   private Money balance;
   private Money amount;
   private Money repayment;
   private Money interest;

   public LocalDate getDate() {
      return date;
   }

   public void setDate(LocalDate date) {
      this.date = date;
   }

   public Money getBalance() {
      return balance;
   }

   public void setBalance(Money balance) {
      this.balance = balance;
   }

   public Money getAmount() {
      return amount;
   }

   public void setAmount(Money amount) {
      this.amount = amount;
   }

   public Money getRepayment() {
      return repayment;
   }

   public void setRepayment(Money repayment) {
      this.repayment = repayment;
   }

   public Money getInterest() {
      return interest;
   }

   public void setInterest(Money interest) {
      this.interest = interest;
   }

   public Money getEndingBalance() {
      return balance.subtract(repayment);
   }
}
