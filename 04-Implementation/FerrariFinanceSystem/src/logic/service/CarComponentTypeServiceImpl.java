package logic.service;

import data.access.CarComponentTypeAccessImpl;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;

public class CarComponentTypeServiceImpl implements CarComponentTypeService {
   @Override
   public void createCarComponentTypes(List<String> types, ConnectionHandler con) throws SQLException {
      new CarComponentTypeAccessImpl(con).createCarComponentTypes(types);
   }
}
