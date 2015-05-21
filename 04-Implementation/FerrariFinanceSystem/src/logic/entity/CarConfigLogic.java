package logic.entity;

import java.sql.SQLException;
import java.util.List;

import util.jdbc.ConnectionHandler;
import domain.CarConfig;

public interface CarConfigLogic {
   void createCarConfig(CarConfig carConfig, ConnectionHandler con) throws SQLException;

   void createCarCar(CarConfig carConfig) throws SQLException;

   List<CarConfig> listCarConfigs() throws SQLException;
}
