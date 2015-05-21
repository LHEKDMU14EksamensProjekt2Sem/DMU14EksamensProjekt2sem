package logic.entity;

import java.sql.SQLException;
import java.util.List;

import util.jdbc.ConnectionHandler;
import domain.Car;

public interface CarLogic {
   void createCar(Car car, ConnectionHandler con) throws SQLException;

   void createCar(Car car) throws SQLException;

   List<Car> listCars() throws SQLException;
}
