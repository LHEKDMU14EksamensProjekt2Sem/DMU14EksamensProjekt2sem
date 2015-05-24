package logic.service;

import data.access.CarComponentAccessImpl;
import domain.CarComponent;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;

public class CarComponentServiceImpl implements CarComponentService {
   @Override
   public void createCarComponents(List<CarComponent> components, ConnectionHandler con) throws SQLException {
      new CarComponentAccessImpl(con).createCarComponents(components);
   }
}
