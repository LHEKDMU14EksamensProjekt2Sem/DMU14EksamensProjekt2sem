package logic.service;

import domain.CarComponent;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;

public interface CarComponentService {
   void createCarComponents(List<CarComponent> components, ConnectionHandler con) throws SQLException;
}
