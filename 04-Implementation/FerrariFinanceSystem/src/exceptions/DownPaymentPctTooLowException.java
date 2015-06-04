package exceptions;

public class DownPaymentPctTooLowException extends ValidationException {
   public DownPaymentPctTooLowException(double downPaymentPct) {
      super("Down payment pct. too low: " + downPaymentPct);
   }
}
