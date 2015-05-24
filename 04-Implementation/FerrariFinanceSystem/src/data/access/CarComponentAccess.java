package data.access;

import domain.CarComponent;
import domain.CarConfig;

import java.sql.SQLException;
import java.util.List;

public interface CarComponentAccess {
   void createCarComponents(List<CarComponent> components) throws SQLException;

   List<CarComponent> listCarComponents(CarConfig config) throws SQLException;
}
