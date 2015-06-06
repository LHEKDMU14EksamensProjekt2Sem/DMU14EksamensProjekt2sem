package logic.calculator;

import java.time.Period;

public enum TermUnit {
   MONTH(Period.ofMonths(1)),
   QUARTER(Period.ofMonths(3)),
   HALF_YEAR(Period.ofMonths(6)),
   YEAR(Period.ofYears(1));

   private final Period period;

   TermUnit(Period period) {
      this.period = period;
   }

   public Period getPeriod() {
      return period;
   }
}
