package logic.util.annuity;

import util.finance.Money;

public class Payment {
   private final Money balance;
   private final Money amount;
   private final Money interestPaid;

   public Payment(Money balance, Money amount, Money interestPaid) {
      this.balance = balance;
      this.amount = amount;
      this.interestPaid = interestPaid;
   }

   public Money getBalance() {
      return balance;
   }

   public Money getAmount() {
      return amount;
   }

   public Money getInterestPaid() {
      return interestPaid;
   }

   public Money getPrincipalPaid() {
      return amount.subtract(interestPaid);
   }

   public Money getEndingBalance() {
      return balance.subtract(getPrincipalPaid());
   }
}
