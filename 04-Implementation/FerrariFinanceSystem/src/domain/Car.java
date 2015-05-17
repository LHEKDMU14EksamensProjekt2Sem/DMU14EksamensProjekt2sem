package domain;

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
}
