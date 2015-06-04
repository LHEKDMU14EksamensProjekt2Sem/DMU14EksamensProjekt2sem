package exceptions;

public class DiscountPctTooHighException extends ValidationException {
   public DiscountPctTooHighException(double discountPct) {
      super("Discount pct. too high: " + discountPct);
   }
}
