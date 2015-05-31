package domain;

import util.finance.Money;

import java.util.Optional;

public class Sale {
   private int id;
   private Employee seller;
   private Customer customer;
   private Optional<Car> car;
   private Money basePrice;
   private Money sellingPrice;

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

   public boolean hasCar() {
      return car.isPresent();
   }

   public Car getCar() {
      return car.get();
   }

   public void setCar(Car car) {
      this.car = Optional.ofNullable(car);
      if (hasCar()) {
         basePrice = car.getBasePrice();
         sellingPrice = basePrice;
      }
   }

   public Money getBasePrice() {
      return basePrice;
   }

   public void setBasePrice(Money basePrice) {
      this.basePrice = basePrice;
   }

   public Money getSellingPrice() {
      return sellingPrice;
   }

   public void setSellingPrice(Money sellingPrice) {
      this.sellingPrice = sellingPrice;
   }

   public Money getDiscount() {
      return basePrice.subtract(sellingPrice);
   }

   public void setDiscount(Money discount) {
      sellingPrice = basePrice.subtract(discount);
   }

   public double getDiscountPct() {
      if (basePrice == Money.ZERO)
         return 0;
      else
         return (1 - sellingPrice.doubleValue() / basePrice.doubleValue());
   }

   public void setDiscountPct(double pct) {
      sellingPrice = new Money(basePrice.doubleValue() * (1 - pct));
   }
}
