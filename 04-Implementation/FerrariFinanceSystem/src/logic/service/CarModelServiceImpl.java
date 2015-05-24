package logic.service;

import data.ConnectionService;
import data.access.CarModelAccessImpl;
import domain.CarModel;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;

public class CarModelServiceImpl implements CarModelService {
   @Override
   public void createCarModels(List<CarModel> carModels, ConnectionHandler con) throws SQLException {
      new CarModelAccessImpl(con).createCarModels(carModels);
   }

   @Override
   public List<CarModel> listCarModels() throws SQLException {
      return ConnectionService.query(con ->
              new CarModelAccessImpl(con).listCarModels());
   }
}
