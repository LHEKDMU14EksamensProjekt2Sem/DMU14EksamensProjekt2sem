package logic.entity;

import java.sql.SQLException;
import java.util.List;

import util.jdbc.ConnectionHandler;
import domain.CarComponant;

public interface CarComponantLogic {
   void createCarComponant(CarComponant carComponant, ConnectionHandler con) throws SQLException;

   void createCarComponant(CarComponant carComponant) throws SQLException;

   List<CarComponant> listCarComponants() throws SQLException;
}
