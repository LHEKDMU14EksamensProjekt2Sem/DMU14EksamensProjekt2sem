package data.access;

import domain.Car;
import domain.CarModel;

import java.sql.SQLException;
import java.util.List;

public interface CarAccess {
   void createCars(List<Car> cars) throws SQLException;

   Car readCar(int id) throws SQLException;

   List<Car> listCars(CarModel model) throws SQLException;
}
