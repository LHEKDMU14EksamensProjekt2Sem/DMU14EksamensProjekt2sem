package logic.service;

import domain.Car;
import domain.CarModel;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CarService {
   void createCars(List<Car> cars, ConnectionHandler con) throws SQLException;

   Optional<Car> readCar(int id) throws SQLException;

   List<Car> listCars(CarModel model) throws SQLException;
}
