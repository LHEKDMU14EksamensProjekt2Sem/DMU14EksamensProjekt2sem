package domain;

import logic.Money;

public class Sale {
   private int id;
   private Employee seller;
   private Customer customer;
   private Money sellingPrice;
   private Money discount;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public Employee getSeller() {
      return seller;
   }

   public void setSeller(Employee seller) {
      this.seller = seller;
   }

   public Customer getCustomer() {
      return customer;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   public Money getSellingPrice() {
      return sellingPrice;
   }

   public void setSellingPrice(Money sellingPrice) {
      this.sellingPrice = sellingPrice;
   }

   public Money getDiscount() {
      return discount;
   }

   public void setDiscount(Money discount) {
      this.discount = discount;
   }
}
