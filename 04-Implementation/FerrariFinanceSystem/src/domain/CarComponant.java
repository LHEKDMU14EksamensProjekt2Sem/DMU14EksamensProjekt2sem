package domain;

import util.finance.Money;

public class CarComponant {
   private int id;
   private CarComponantType carComponantType;
   private String name;
   private String description;
   private Money basePrice;
   
   
   public int getId() {
      return id;
   }
   public void setId( int id ) {
      this.id = id;
   }
   public CarComponantType getCarComponantType() {
      return carComponantType;
   }
   public void setCarComponantType( CarComponantType carComponantType ) {
      this.carComponantType = carComponantType;
   }
   public String getName() {
      return name;
   }
   public void setName( String name ) {
      this.name = name;
   }
   public String getDescription() {
      return description;
   }
   public void setDescription( String description ) {
      this.description = description;
   }
   public Money getBasePrice() {
      return basePrice;
   }
   public void setBasePrice( Money basePrice ) {
      this.basePrice = basePrice;
   }
}
