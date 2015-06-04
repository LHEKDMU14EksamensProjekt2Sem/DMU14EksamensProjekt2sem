package data.access;

import domain.CarConfig;

import java.sql.SQLException;
import java.util.List;

public interface CarConfigAccess {
   void createCarConfigs(List<CarConfig> carConfigs) throws SQLException;
}
