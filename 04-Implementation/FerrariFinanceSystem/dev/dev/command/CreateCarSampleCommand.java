package dev.command;

import domain.Car;
import domain.CarComponent;
import domain.CarComponentType;
import domain.CarConfig;
import domain.CarModel;
import logic.service.*;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import static dev.sample.CarSample.*;

public class CreateCarSampleCommand implements Callable<List<Car>> {
   private final ConnectionHandler con;

   public CreateCarSampleCommand(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public List<Car> call() throws SQLException {
      createCarComponentTypes();
      List<CarComponent> components = createCarComponents();
      List<CarModel> models = createCarModels();
      List<CarConfig> configs = createCarConfigs(models, components);
      return createCars(configs);
   }

   private List<Car> createCars(List<CarConfig> configs) throws SQLException {
      List<Car> cars = newCars(configs);
      CarService service = new CarServiceImpl();
      service.createCars(cars, con);
      return cars;
   }

   private List<CarConfig> createCarConfigs(List<CarModel> models,
                                            List<CarComponent> components) throws SQLException {
      List<CarConfig> configs = newCarConfigs(models, components);
      CarConfigService service = new CarConfigServiceImpl();
      service.createCarConfigs(configs, con);
      return configs;
   }

   private List<CarModel> createCarModels() throws SQLException {
      List<CarModel> models = newCarModels();
      CarModelService logic = new CarModelServiceImpl();
      logic.createCarModels(models, con);
      return models;
   }

   private List<CarComponent> createCarComponents() throws SQLException {
      List<CarComponent> components = newCarComponents();
      CarComponentService service = new CarComponentServiceImpl();
      service.createCarComponents(components, con);
      return components;
   }

   private void createCarComponentTypes() throws SQLException {
      List<CarComponentType> types = Arrays.asList(CarComponentType.values());
      CarComponentTypeService service = new CarComponentTypeServiceImpl();
      service.createCarComponentTypes(types, con);
   }
}
