package logic.service;

import domain.CarComponentType;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;

public interface CarComponentTypeService {
   void createCarComponentTypes(List<CarComponentType> types, ConnectionHandler con) throws SQLException;
}
