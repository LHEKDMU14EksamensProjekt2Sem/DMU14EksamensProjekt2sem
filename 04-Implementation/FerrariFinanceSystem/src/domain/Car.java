package domain;

import util.finance.Money;

public class Car {
   private int id;
   private CarConfig config;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public CarConfig getConfig() {
      return config;
   }

   public void setConfig(CarConfig config) {
      this.config = config;
   }

   public Money getBasePrice() {
      return config.getBasePrice();
   }

   @Override
   public String toString() {
      return (config.getName() + " [" + id + "]");
   }
}
