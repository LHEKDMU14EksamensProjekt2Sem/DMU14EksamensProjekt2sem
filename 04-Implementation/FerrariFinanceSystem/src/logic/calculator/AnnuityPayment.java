package logic.calculator;

import util.finance.Money;

public class AnnuityPayment implements Payment {
   private final Money balance;
   private final Money amount;
   private final Money repayment;
   private final Money interest;

   public AnnuityPayment(Money balance, Money amount, Money repayment, Money interest) {
      this.balance = balance;
      this.amount = amount;
      this.repayment = repayment;
      this.interest = interest;
   }

   @Override
   public Money getBalance() {
      return balance;
   }

   @Override
   public Money getAmount() {
      return amount;
   }

   @Override
   public Money getRepayment() {
      return repayment;
   }

   @Override
   public Money getInterest() {
      return interest;
   }

   @Override
   public Money getEndingBalance() {
      return balance.subtract(repayment);
   }
}
