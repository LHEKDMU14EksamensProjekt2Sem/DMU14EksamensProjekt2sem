package domain;

import util.finance.Money;

import java.time.LocalDate;
import java.util.Optional;

public class LoanRequest {
   private Sale sale;
   private LoanRequestStatus status;
   private Employee statusByEmployee;
   private LocalDate date;
   private Money loanAmount;
   private Optional<Money> prefPayment;
   private Optional<Integer> prefTerm;

   public LoanRequest() {
      prefPayment = Optional.empty();
      prefTerm = Optional.empty();
   }

   public int getId() {
      return sale.getId();
   }

   public Sale getSale() {
      return sale;
   }

   public void setSale(Sale sale) {
      this.sale = sale;
   }

   public LoanRequestStatus getStatus() {
      return status;
   }

   public void setStatus(LoanRequestStatus status) {
      this.status = status;
   }

   public Employee getStatusByEmployee() {
      return statusByEmployee;
   }

   public void setStatusByEmployee(Employee statusByEmployee) {
      this.statusByEmployee = statusByEmployee;
   }

   public LocalDate getDate() {
      return date;
   }

   public void setDate(LocalDate date) {
      this.date = date;
   }

   public Money getLoanAmount() {
      return loanAmount;
   }

   public void setLoanAmount(Money loanAmount) {
      this.loanAmount = loanAmount;
   }

   public Money getDownPayment() {
      return sale.getSellingPrice().subtract(loanAmount);
   }

   public void setDownPayment(Money downPayment) {
      loanAmount = sale.getSellingPrice().subtract(downPayment);
   }

   public double getDownPaymentPct() {
      if (sale.getSellingPrice() == Money.ZERO)
         return 0;
      else
         return (getDownPayment().doubleValue() / sale.getSellingPrice().doubleValue());
   }

   public void setDownPaymentPct(double pct) {
      loanAmount = new Money(sale.getSellingPrice().doubleValue() * (1 - pct));
   }

   public boolean hasPreferredPayment() {
      return prefPayment.isPresent();
   }

   public Money getPreferredPayment() {
      return prefPayment.get();
   }

   public void setPreferredPayment(Money prefPayment) {
      this.prefPayment = Optional.ofNullable(prefPayment);
   }

   public boolean hasPreferredTerm() {
      return prefTerm.isPresent();
   }

   public Integer getPreferredTerm() {
      return prefTerm.get();
   }

   public void setPreferredTerm(Integer prefTerm) {
      this.prefTerm = Optional.ofNullable(prefTerm);
   }
}
