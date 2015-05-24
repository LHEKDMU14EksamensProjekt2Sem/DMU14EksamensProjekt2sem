package logic.service;

import data.access.CarConfigAccessImpl;
import data.access.CarConfigComponentAccess;
import data.access.CarConfigComponentAccessImpl;
import domain.CarConfig;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;

public class CarConfigServiceImpl implements CarConfigService {
   @Override
   public void createCarConfigs(List<CarConfig> configs, ConnectionHandler con)
           throws SQLException {
      new CarConfigAccessImpl(con).createCarConfigs(configs);

      CarConfigComponentAccess access = new CarConfigComponentAccessImpl(con);
      for (CarConfig config : configs) {
         access.createCarConfigComponents(config);
      }
   }
}
