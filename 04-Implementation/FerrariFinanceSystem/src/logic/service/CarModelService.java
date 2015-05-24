package logic.service;

import domain.CarModel;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;

public interface CarModelService {
   void createCarModels(List<CarModel> carModels, ConnectionHandler con) throws SQLException;

   List<CarModel> listCarModels() throws SQLException;
}
