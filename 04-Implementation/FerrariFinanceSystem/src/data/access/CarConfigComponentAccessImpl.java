package data.access;

import domain.CarComponent;
import domain.CarConfig;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarConfigComponentAccessImpl implements CarConfigComponentAccess {
   private final ConnectionHandler con;

   public CarConfigComponentAccessImpl(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void createCarConfigComponents(CarConfig config) throws SQLException {
      deleteCarConfigComponents(config);

      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
         for (CarComponent comp : config.getComponents()) {
            st.setInt(1, config.getId());
            st.setInt(2, comp.getId());
            st.executeUpdate();
         }
      }
   }

   private void deleteCarConfigComponents(CarConfig config) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.DELETE_MANY)) {
         st.setInt(1, config.getId());
         st.executeUpdate();
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO car_config_component(config_id, component_id) VALUES (?, ?)";

      static final String DELETE_MANY
              = "DELETE FROM car_config_component WHERE config_id = ?";
   }
}
