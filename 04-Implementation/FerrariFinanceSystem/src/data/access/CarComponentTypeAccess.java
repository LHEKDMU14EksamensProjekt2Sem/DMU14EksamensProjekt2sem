package data.access;

import domain.CarComponentType;

import java.sql.SQLException;
import java.util.List;

public interface CarComponentTypeAccess {
   void createCarComponentTypes(List<CarComponentType> types) throws SQLException;
}
