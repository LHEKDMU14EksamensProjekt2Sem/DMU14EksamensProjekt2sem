package domain;

import util.finance.Money;

public interface AnnuityLoan {
   Money getPrincipal();

   double getInterestRate();
}
