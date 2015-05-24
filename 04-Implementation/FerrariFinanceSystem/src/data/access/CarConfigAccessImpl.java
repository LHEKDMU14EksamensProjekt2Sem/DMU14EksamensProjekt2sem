package data.access;

import domain.CarConfig;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.sql.Statement.*;

public class CarConfigAccessImpl implements CarConfigAccess {
   private ConnectionHandler con;

   public CarConfigAccessImpl(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void createCarConfigs(List<CarConfig> configs) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(
              SQL.INSERT_ONE, RETURN_GENERATED_KEYS)) {
         for (CarConfig config : configs) {
            st.setInt(1, config.getModel().getId());
            st.setString(2, config.getName());
            st.setString(3, config.getDescription());
            st.executeUpdate();

            try (ResultSet rs = st.getGeneratedKeys()) {
               rs.next();
               config.setId(rs.getInt(1));
            }
         }
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO car_config (model_id, name, description) VALUES (?, ?, ?)";
   }
}
