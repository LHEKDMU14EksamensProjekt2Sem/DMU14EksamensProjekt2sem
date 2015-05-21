package logic.entity;

import java.sql.SQLException;
import java.util.List;

import util.jdbc.ConnectionHandler;
import domain.CarConfigComponant;

public interface CarConfigComponantLogic {
   void createCarConfigComponant(CarConfigComponant carConfigComponant, ConnectionHandler con) throws SQLException;

   void createCar(CarConfigComponant carConfigComponant) throws SQLException;

   List<CarConfigComponant> listCarConfigComponants() throws SQLException;
}
