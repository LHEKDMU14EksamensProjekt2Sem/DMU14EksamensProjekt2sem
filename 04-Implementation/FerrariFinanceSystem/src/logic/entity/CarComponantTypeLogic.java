package logic.entity;

import java.sql.SQLException;
import java.util.List;

import util.jdbc.ConnectionHandler;
import domain.CarComponantType;

public interface CarComponantTypeLogic {
   void createCarComponantType(CarComponantType carComponantType, ConnectionHandler con) throws SQLException;

   void createCarComponantType(CarComponantType carComponantType) throws SQLException;

   List<CarComponantType> listCarComponantTypes() throws SQLException;
}
