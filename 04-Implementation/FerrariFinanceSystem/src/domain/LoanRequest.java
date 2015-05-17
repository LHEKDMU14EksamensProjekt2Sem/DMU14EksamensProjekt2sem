package domain;

import logic.LoanRequestStatus;
import logic.Money;

import java.sql.Date;

public class LoanRequest {
   private Sale sale;
   private LoanRequestStatus status;
   private Employee statusByEmployee;
   private Date date;
   private Money loanAmount;
   private Money preferredRepayment;
   private Integer preferredTerm;

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

   public boolean hasPreferredRepayment() {
      return (preferredRepayment != null);
   }

   public Money getPreferredRepayment() {
      return preferredRepayment;
   }

   public void setPreferredRepayment(Money preferredRepayment) {
      this.preferredRepayment = preferredRepayment;
   }

   public boolean hasPreferredTerm() {
      return (preferredTerm != null);
   }

   public Integer getPreferredTerm() {
      return preferredTerm;
   }

   public void setPreferredTerm(Integer preferredTerm) {
      this.preferredTerm = preferredTerm;
   }
}
