package domain;

import logic.Money;

public class CarModel {
   private int id;
   private int year;
   private String name;
   private String description;
   private Money basePrice;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getYear() {
      return year;
   }

   public void setYear(int year) {
      this.year = year;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Money getBasePrice() {
      return basePrice;
   }

   public void setBasePrice(Money basePrice) {
      this.basePrice = basePrice;
   }
}
