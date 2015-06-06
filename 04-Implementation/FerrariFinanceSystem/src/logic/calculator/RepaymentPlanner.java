package logic.calculator;

import domain.AnnuityLoan;
import domain.RepaymentPlanPayment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepaymentPlanner {
   private AnnuityCalculator calculator;
   private TermUnit unit;

   public RepaymentPlanner(AnnuityCalculator calculator, TermUnit unit) {
      this.calculator = calculator;
      this.unit = unit;
   }

   public List<RepaymentPlanPayment> listPaymentsFor(AnnuityLoan loan, int term, LocalDate startsOn) {
      List<Payment> payments = calculator.computePayments(
              loan.getPrincipal(), loan.getInterestRate(), term);

      List<RepaymentPlanPayment> plan = new ArrayList<>();
      LocalDate date = startsOn;
      for (Payment p : payments) {
         RepaymentPlanPayment rpp = new RepaymentPlanPayment();
         rpp.setDate(date);
         rpp.setBalance(p.getBalance());
         rpp.setAmount(p.getAmount());
         rpp.setRepayment(p.getRepayment());
         rpp.setInterest(p.getInterest());
         plan.add(rpp);

         date = date.plus(unit.getPeriod());
      }

      return plan;
   }
}
