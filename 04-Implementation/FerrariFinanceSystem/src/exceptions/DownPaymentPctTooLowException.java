package exceptions;

public class DownPaymentPctTooLowException extends Exception {
   public DownPaymentPctTooLowException(double downPaymentPct) {
      super("Down payment pct. too low: " + downPaymentPct);
   }
}
