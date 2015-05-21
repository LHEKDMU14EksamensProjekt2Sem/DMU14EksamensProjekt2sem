package logic.entity;

import domain.CarModel;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;

public interface CarModelLogic {
   void createCarModels(List<CarModel> carModels, ConnectionHandler con) throws SQLException;

   List<CarModel> listCarModels() throws SQLException;
}
