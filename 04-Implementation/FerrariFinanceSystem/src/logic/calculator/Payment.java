package logic.calculator;

import util.finance.Money;

public interface Payment {
   Money getBalance();

   Money getAmount();

   Money getRepayment();

   Money getInterest();

   Money getEndingBalance();
}
