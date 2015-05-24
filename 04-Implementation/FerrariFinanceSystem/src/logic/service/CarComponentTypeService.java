package logic.service;

import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;

public interface CarComponentTypeService {
   void createCarComponentTypes(List<String> types, ConnectionHandler con) throws SQLException;
}
