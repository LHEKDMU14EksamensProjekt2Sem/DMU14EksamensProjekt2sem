import loan.Repayment;
import loan.RepaymentCalculator;
import loan.RepaymentPlan;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Calendar;

public class Main {
   public static void main(String[] args) {
      BigDecimal principal = new BigDecimal("1800000");
      BigDecimal payment = new BigDecimal("150");
      double interestRate = 0.1;
      int term = 12;
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.DATE, 1);

      RepaymentPlan plan = new RepaymentPlan(new RepaymentCalculator(), principal, interestRate, term, cal);
//      RepaymentPlan plan = new RepaymentPlan(new RepaymentCalculator(), principal, interestRate, payment, cal);

      print(plan);
   }

   private static void print(RepaymentPlan plan) {
      System.out.println("Repayment Plan for Loan #177322");
      System.out.println("-----------------------------------------------------------------------------------");
      System.out.println("Principal: " + plan.getPrincipal());
      System.out.println("Term: " + plan.getTerm() + " months");
      System.out.println("Interest rate pa: " + (plan.getInterestRate() * 100) + "%");
      System.out.println("Effective APR: " + (plan.getEffectiveAPR() * 100) + "%");
      System.out.println("Total amount payable: " + plan.getTotalAmountPayable());
      System.out.println("Total charge: " + plan.getTotalCharge());
      System.out.println("-----------------------------------------------------------------------------------");

      printField("Date");
      printField("Payment");
      printField("Principal paid");
      printField("Interest paid");
      printField("Ending balance");
      System.out.println();
      System.out.println();

      DateFormat df = DateFormat.getDateInstance(DateFormat.DATE_FIELD);
      for (Repayment r : plan.getRepayments()) {
         printField(df.format(r.getDate()));
         printField(r.getPayment());
         printField(r.getPrincipalPaid());
         printField(r.getInterestPaid());
         printField(r.getEndingBalance());
         System.out.println();
      }
   }

   private static void printField(Object value) {
      String s = "                " + value;
      System.out.print(s.substring(s.length() - 16));
   }
}
