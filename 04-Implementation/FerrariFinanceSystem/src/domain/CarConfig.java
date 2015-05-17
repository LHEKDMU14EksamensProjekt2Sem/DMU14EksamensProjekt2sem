package domain;

public class CarConfig {
   private int id;
   private CarModel model;
   private String name;
   private String description;

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
}
