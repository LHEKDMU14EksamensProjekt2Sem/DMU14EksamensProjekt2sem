package util.finance;

import java.math.BigDecimal;

import static java.math.RoundingMode.*;

public final class Money {
   public static final Money ZERO = new Money(BigDecimal.ZERO);

   private final BigDecimal amount;

   public Money(String amount) {
      this(new BigDecimal(amount));
   }

   public Money(double amount) {
      this(new BigDecimal(amount));
   }

   public Money(BigDecimal amount) {
      this.amount = scale(amount);
   }

   private static BigDecimal scale(BigDecimal amount) {
      return amount.setScale(2, HALF_UP);
   }

   public Money add(Money augend) {
      return new Money(amount.add(augend.amount));
   }

   public Money subtract(Money subtrahend) {
      return new Money(amount.subtract(subtrahend.amount));
   }

   public Money divide(Money divisor) {
      return new Money(amount.divide(divisor.amount, HALF_UP));
   }

   public Money multiply(Money multiplicand) {
      return new Money(scale(
              amount.multiply(multiplicand.amount)));
   }

   public BigDecimal asBigDecimal() {
      return amount;
   }

   public double doubleValue() {
      return amount.doubleValue();
   }

   @Override
   public boolean equals(Object object) {
      if (this == object)
         return true;
      else if (!(object instanceof Money))
         return false;

      Money other = (Money) object;
      return amount.equals(other.amount);
   }

   @Override
   public String toString() {
      return amount.toString();
   }
}
