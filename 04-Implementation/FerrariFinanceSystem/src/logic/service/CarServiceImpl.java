package logic.service;

import data.ConnectionService;
import data.access.CarAccessImpl;
import domain.Car;
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
      return ConnectionService.query(con ->
              new CarAccessImpl(con).readCar(id));
   }

   @Override
   public List<Car> listCars(CarModel model) throws SQLException {
      return ConnectionService.query(con ->
              new CarAccessImpl(con).listCars(model));
   }
}
