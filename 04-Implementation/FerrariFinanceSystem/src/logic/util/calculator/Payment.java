package logic.util.calculator;

import util.finance.Money;

public class Payment {
   private final Money balance;
   private final Money amount;
   private final Money principalPaid;
   private final Money interestPaid;

   public Payment(Money balance, Money amount, Money principalPaid, Money interestPaid) {
      this.balance = balance;
      this.amount = amount;
      this.principalPaid = principalPaid;
      this.interestPaid = interestPaid;
   }

   public Money getBalance() {
      return balance;
   }

   public Money getAmount() {
      return amount;
   }

   public Money getPrincipalPaid() {
      return principalPaid;
   }

   public Money getInterestPaid() {
      return interestPaid;
   }

   public Money getEndingBalance() {
      return balance.subtract(getPrincipalPaid());
   }
}
