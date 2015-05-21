package domain;

public class CarConfigComponant {
   private CarConfig config;
   private CarComponant componant;
   public CarConfig getConfig() {
      return config;
   }
   public void setConfig( CarConfig config ) {
      this.config = config;
   }
   public CarComponant getComponant() {
      return componant;
   }
   public void setComponant( CarComponant componant ) {
      this.componant = componant;
   }
}
