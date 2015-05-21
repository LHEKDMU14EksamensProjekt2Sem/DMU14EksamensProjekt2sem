package logic.entity;

import java.sql.SQLException;
import java.util.List;

import util.jdbc.ConnectionHandler;
import data.ConnectionService;
import data.access.CarAccess;
import domain.CarModel;

public class CarModelLogicImpl implements CarModelLogic {
   @Override
   public void createCarModel(CarModel carModel, ConnectionHandler con) throws SQLException {
      new CarAccess(con).createEmployee( carModel );
   }

   @Override
   public void createCarModel(CarModel carModel) throws SQLException {
      ConnectionService.execute(con ->
              createCarModel(carModel, con));
   }

   @Override
   public List<CarModel> listCarModels() throws SQLException {
      return ConnectionService.query(con ->
              new CarAccess(con).readCarModels());
   }
}
