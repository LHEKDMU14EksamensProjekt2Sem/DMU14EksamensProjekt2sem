package data.access;

import domain.Car;
import domain.CarModel;
import domain.Sale;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CarAccess {
   void createCars(List<Car> cars) throws SQLException;

   Optional<Car> readCar(int id) throws SQLException;

   Car readCar(Sale sale) throws SQLException;

   List<Car> listCars(CarModel model) throws SQLException;
}
