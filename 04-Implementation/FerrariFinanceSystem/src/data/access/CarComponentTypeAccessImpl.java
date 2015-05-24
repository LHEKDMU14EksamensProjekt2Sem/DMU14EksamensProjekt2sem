package data.access;

import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CarComponentTypeAccessImpl implements CarComponentTypeAccess {
   private final ConnectionHandler con;

   public CarComponentTypeAccessImpl(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void createCarComponentTypes(List<String> types) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
         for (String type : types) {
            st.setString(1, type);
            st.executeUpdate();
         }
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO car_component_type(type) VALUES(?)";
   }
}
