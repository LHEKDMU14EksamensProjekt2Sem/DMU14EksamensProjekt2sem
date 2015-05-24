package domain;

import util.finance.Money;

import java.sql.Date;
import java.util.Optional;

public class LoanRequest {
   private Sale sale;
   private LoanRequestStatus status;
   private Employee statusByEmployee;
   private Date date;
   private Money loanAmount;
   private Optional<Money> prefRepayment;
   private Optional<Integer> prefTerm;

   public LoanRequest() {
      prefRepayment = Optional.empty();
      prefTerm = Optional.empty();
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

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
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
      return (getDownPayment().doubleValue() / sale.getSellingPrice().doubleValue());
   }

   public void setDownPaymentPct(double pct) {
      loanAmount = new Money(sale.getSellingPrice().doubleValue() * (1 - pct));
   }

   public boolean hasPreferredRepayment() {
      return prefRepayment.isPresent();
   }

   public Money getPreferredRepayment() {
      return prefRepayment.get();
   }

   public void setPreferredRepayment(Money prefRepayment) {
      this.prefRepayment = Optional.ofNullable(prefRepayment);
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
