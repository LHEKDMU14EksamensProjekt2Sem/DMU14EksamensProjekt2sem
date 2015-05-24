package data.access;

import domain.CarModel;

import java.sql.SQLException;
import java.util.List;

public interface CarModelAccess {
   void createCarModels(List<CarModel> carModels) throws SQLException;

   List<CarModel> listCarModels() throws SQLException;
}
