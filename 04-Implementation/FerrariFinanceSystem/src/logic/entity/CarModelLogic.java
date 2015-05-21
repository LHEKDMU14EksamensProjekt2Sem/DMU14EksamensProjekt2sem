package logic.entity;

import domain.CarModel;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;

public interface CarModelLogic {
   void createCarModel(CarModel carModel, ConnectionHandler con) throws SQLException;

   void createCarModel(CarModel carModel) throws SQLException;

   List readCarModel(ConnectionHandler con) throws SQLException;

   List readCarModel() throws SQLException;
}
