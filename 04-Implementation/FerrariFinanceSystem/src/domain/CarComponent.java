package domain;

import util.finance.Money;

public class CarComponent {
   private int id;
   private String type;
   private String name;
   private String description;
   private Money basePrice;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
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
