package data.access;

import java.sql.SQLException;
import java.util.List;

import domain.Car;
import domain.CarConfig;

public interface CarConfigAccess {
   void createCarConfigs(List<CarConfig> carConfigs) throws SQLException;
}
