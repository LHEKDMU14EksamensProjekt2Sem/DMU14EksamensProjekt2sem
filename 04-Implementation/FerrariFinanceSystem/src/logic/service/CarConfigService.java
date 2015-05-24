package logic.service;

import java.sql.SQLException;
import java.util.List;

import util.jdbc.ConnectionHandler;
import domain.CarConfig;

public interface CarConfigService {
   void createCarConfigs(List<CarConfig> configs, ConnectionHandler con) throws SQLException;
}
