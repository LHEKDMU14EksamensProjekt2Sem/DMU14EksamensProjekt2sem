package exceptions;

public class DiscountPctTooHighException extends Exception {
   public DiscountPctTooHighException(double discountPct) {
      super("Discount pct. too high: " + discountPct);
   }
}
