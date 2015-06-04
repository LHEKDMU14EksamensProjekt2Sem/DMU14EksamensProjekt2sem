package dev.sample;

import domain.Car;
import domain.CarComponent;
import domain.CarComponentType;
import domain.CarConfig;
import domain.CarModel;
import util.finance.Money;

import java.util.Arrays;
import java.util.List;

public class CarSample {
   // Cars
   // Require configs
   ////////////////////

   public static List<Car> newCars(List<CarConfig> configs) {
      return Arrays.asList(
              newCar(configs.get(0)),
              newCar(configs.get(0)),
              newCar(configs.get(1)),
              newCar(configs.get(2)),
              newCar(configs.get(3)),
              newCar(configs.get(3)),
              newCar(configs.get(3)),
              newCar(configs.get(4)),
              newCar(configs.get(5)),
              newCar(configs.get(6)),
              newCar(configs.get(6)),
              newCar(configs.get(7)),
              newCar(configs.get(7)));
   }

   private static Car newCar(CarConfig config) {
      Car car = new Car();
      car.setConfig(config);
      return car;
   }

   // Configs
   // Require models and components
   //////////////////////////////////

   public static List<CarConfig> newCarConfigs(List<CarModel> models,
                                               List<CarComponent> components) {
      return Arrays.asList(
              newCarConfig(models.get(0), "Lion roar III", "Lorem ipsum", components.subList(0, 4)),
              newCarConfig(models.get(0), "Lion roar V", "Lorem ipsum", components.subList(4, 8)),
              newCarConfig(models.get(1), "Old Oak", "Lorem ipsum", components.subList(0, 4)),
              newCarConfig(models.get(2), "Spicey runner", "Lorem ipsum", components.subList(4, 8)),
              newCarConfig(models.get(3), "Warm Owl II", "Lorem ipsum", components.subList(0, 4)),
              newCarConfig(models.get(4), "Shifty", "Lorem ipsum", components.subList(4, 8)),
              newCarConfig(models.get(5), "Roadster I", "Lorem ipsum", components.subList(0, 4)),
              newCarConfig(models.get(5), "Roadster II", "Lorem ipsum", components.subList(4, 8)));
   }

   private static CarConfig newCarConfig(CarModel model,
                                         String name,
                                         String description,
                                         List<CarComponent> components) {
      CarConfig config = new CarConfig();
      config.setModel(model);
      config.setName(name);
      config.setDescription(description);
      config.setComponents(components);
      return config;
   }

   // Models
   ///////////

   public static List<CarModel> newCarModels() {
      return Arrays.asList(
              newCarModel(2014, "Ferrari California T", "description", "3395000"),
              newCarModel(2015, "488 GTB", "description", "7459000.00"),
              newCarModel(2010, "458 ITALIA", "description", "4390000.00"),
              newCarModel(2013, "F12BERLINETTA", "description", "5905000.00"),
              newCarModel(2011, "FF", "description", "5280000.00"),
              newCarModel(2013, "LaFERRARI", "description", "2995000.00"));
   }

   private static CarModel newCarModel(int year, String name, String description, String price) {
      CarModel m = new CarModel();
      m.setYear(year);
      m.setName(name);
      m.setDescription(description);
      m.setBasePrice(new Money(price));
      return m;
   }

   // Components
   // Require component types
   ////////////////////////////

   public static List<CarComponent> newCarComponents() {
      return Arrays.asList(
              newCarComponent(CarComponentType.PAINT, "Pale yellow", "Lorem ipsum", "12000"),
              newCarComponent(CarComponentType.RADIO, "FM radio", "Lorem ipsum", "8000"),
              newCarComponent(CarComponentType.ENGINE, "V8 Inca", "Lorem ipsum", "210000"),
              newCarComponent(CarComponentType.RIMS, "Spike II", "Lorem ipsum", "43000"),
              newCarComponent(CarComponentType.PAINT, "Ferrari red", "Lorem ipsum", "32000"),
              newCarComponent(CarComponentType.RADIO, "Cassette player", "Lorem ipsum", "11500"),
              newCarComponent(CarComponentType.ENGINE, "V8 Thunder", "Lorem ipsum", "190000"),
              newCarComponent(CarComponentType.RIMS, "Magnets", "Lorem ipsum", "31000"));
   }

   private static CarComponent newCarComponent(CarComponentType type,
                                               String name,
                                               String description,
                                               String basePrice) {
      CarComponent comp = new CarComponent();
      comp.setType(type);
      comp.setName(name);
      comp.setDescription(description);
      comp.setBasePrice(new Money(basePrice));
      return comp;
   }
}
