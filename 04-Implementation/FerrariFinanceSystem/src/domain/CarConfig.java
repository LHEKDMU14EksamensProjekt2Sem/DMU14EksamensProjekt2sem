package domain;

import util.finance.Money;

import java.util.Collections;
import java.util.List;

public class CarConfig {
   private int id;
   private CarModel model;
   private String name;
   private String description;
   private List<CarComponent> components;

   public CarConfig() {
      components = Collections.EMPTY_LIST;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public CarModel getModel() {
      return model;
   }

   public void setModel(CarModel model) {
      this.model = model;
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

   public List<CarComponent> getComponents() {
      return components;
   }

   public void setComponents(List<CarComponent> components) {
      this.components = components;
   }

   public Money getBasePrice() {
      Money price = Money.ZERO;
      for (CarComponent comp : components)
         price = price.add(comp.getBasePrice());

      return price.add(model.getBasePrice());
   }
}
