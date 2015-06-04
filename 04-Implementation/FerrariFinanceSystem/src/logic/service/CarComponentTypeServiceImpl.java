package logic.service;

import data.access.CarComponentTypeAccessImpl;
import domain.CarComponentType;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;

public class CarComponentTypeServiceImpl implements CarComponentTypeService {
   @Override
   public void createCarComponentTypes(List<CarComponentType> types, ConnectionHandler con) throws SQLException {
      new CarComponentTypeAccessImpl(con).createCarComponentTypes(types);
   }
}
