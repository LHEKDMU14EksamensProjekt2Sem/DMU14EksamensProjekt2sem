package logic.service;

import data.ConnectionService;
import data.access.CarAccessImpl;
import data.access.CarComponentAccessImpl;
import domain.Car;
import domain.CarComponent;
import domain.CarConfig;
import domain.CarModel;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CarServiceImpl implements CarService {
   @Override
   public void createCars(List<Car> cars, ConnectionHandler con) throws SQLException {
      new CarAccessImpl(con).createCars(cars);
   }

   @Override
   public Optional<Car> readCar(int id) throws SQLException {
      return ConnectionService.query(con -> {
         Optional<Car> res = new CarAccessImpl(con).readCar(id);

         if (res.isPresent()) {
            CarConfig config = res.get().getConfig();
            List<CarComponent> components = new CarComponentAccessImpl(con).listCarComponents(config);
            config.setComponents(components);
         }

         return res;
      });
   }

   @Override
   public List<Car> listCars(CarModel model) throws SQLException {
      return ConnectionService.query(con -> {
         List<Car> res = new CarAccessImpl(con).listCars(model);

         for (Car car : res) {
            CarConfig config = car.getConfig();
            List<CarComponent> components = new CarComponentAccessImpl(con).listCarComponents(config);
            config.setComponents(components);
         }

         return res;
      });
   }
}
