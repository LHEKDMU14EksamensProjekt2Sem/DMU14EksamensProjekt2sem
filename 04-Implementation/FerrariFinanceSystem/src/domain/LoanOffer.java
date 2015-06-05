package domain;

import util.finance.Money;

import java.time.LocalDate;

public class LoanOffer {
   private int id;
   private LocalDate date;
   private Money principal;
   private double interestRate;

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

   public Money getPrincipal() {
      return principal;
   }

   public void setPrincipal(Money principal) {
      this.principal = principal;
   }

   public double getInterestRate() {
      return interestRate;
   }

   public void setInterestRate(double interestRate) {
      this.interestRate = interestRate;
   }
}
