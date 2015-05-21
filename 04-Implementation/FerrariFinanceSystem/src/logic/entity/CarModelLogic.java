package logic.entity;

import java.sql.SQLException;
import java.util.List;

import util.jdbc.ConnectionHandler;
import data.ConnectionHandlerFactory;
import data.access.CarAccess;
import domain.CarModel;

public class CarModelLogic {
   public void createCarModel(CarModel carModel, ConnectionHandler con) throws SQLException {
      new CarAccess(con).createEmployee( carModel );
   }
   
   public void createCarModel(CarModel carModel) throws SQLException {
      try (ConnectionHandler con = ConnectionHandlerFactory.create()) {
         try {
            createCarModel(carModel, con);
            con.commit();
         } catch (SQLException e) {
            con.rollback();
            throw e;
         }
      }
   }
   
   public List readCarModel( ConnectionHandler con) throws SQLException {
      return new CarAccess(con).readCarModels();
   }
   
   public List readCarModel() throws SQLException {
      try (ConnectionHandler con = ConnectionHandlerFactory.create()) {
         try {
            return readCarModel(con);
         } catch (SQLException e) {
            throw e;
         }
      }
   }
   
   
}
