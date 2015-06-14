package domain;

import util.finance.Money;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class LoanOffer implements AnnuityLoan {
   private int id;
   private LocalDate date;
   private Money principal;
   private double interestRate;
   private List<RepaymentPlanPayment> payments = Collections.emptyList();
   private LoanRequest loanRequest;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public LocalDate getDate() {
      return date;
   }

   public void setDate(LocalDate date) {
      this.date = date;
   }

   @Override
   public Money getPrincipal() {
      return principal;
   }

   public void setPrincipal(Money principal) {
      this.principal = principal;
   }

   @Override
   public double getInterestRate() {
      return interestRate;
   }

   public void setInterestRate(double interestRate) {
      this.interestRate = interestRate;
   }

   public List<RepaymentPlanPayment> getPayments() {
      return payments;
   }

   public void setPayments(List<RepaymentPlanPayment> payments) {
      this.payments = payments;
   }

   public int getTerm() {
      return payments.size();
   }

   public Money getMonthlyPayment() {
      return payments.get(0).getAmount();
   }

   public LocalDate getDateOfFirstPayment() {
      return payments.get(0).getDate();
   }

   public LocalDate getDateOfLastPayment() {
      return payments.get(payments.size() - 1).getDate();
   }

   public LoanRequest getLoanRequest() {
      return loanRequest;
   }

   public void setLoanRequest(LoanRequest loanRequest) {
      this.loanRequest = loanRequest;
   }

   public Money getSumOfPayments() {
      Money sum = Money.ZERO;
      for (RepaymentPlanPayment p : payments)
         sum = sum.add(p.getAmount());
      return sum;
   }

   public Money getTotalCost() {
      Money sum = Money.ZERO;
      for (RepaymentPlanPayment p : payments)
         sum = sum.add(p.getInterest());
      return sum;
   }

   public double getAPR() {
      double costPct = (getSumOfPayments().subtract(principal).doubleValue() / principal.doubleValue());
      return (costPct / payments.size() * 12);
   }
}
