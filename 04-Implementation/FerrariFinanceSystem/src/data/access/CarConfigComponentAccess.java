package data.access;

import domain.CarConfig;

import java.sql.SQLException;

public interface CarConfigComponentAccess {
   void createCarConfigComponents(CarConfig config) throws SQLException;
}
