package data.access;

import domain.CarComponent;
import domain.CarComponentType;
import domain.CarConfig;
import util.finance.Money;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarComponentAccessImpl implements CarComponentAccess {
   private final ConnectionHandler con;

   public CarComponentAccessImpl(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void createCarComponents(List<CarComponent> components) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(
              SQL.INSERT_ONE, Statement.RETURN_GENERATED_KEYS)) {
         for (CarComponent comp : components) {
            st.setString(1, comp.getType().toString());
            st.setString(2, comp.getName());
            st.setString(3, comp.getDescription());
            st.setBigDecimal(4, comp.getBasePrice().asBigDecimal());
            st.executeUpdate();

            try (ResultSet rs = st.getGeneratedKeys()) {
               rs.next();
               comp.setId(rs.getInt(1));
            }
         }
      }
   }

   @Override
   public List<CarComponent> listCarComponents(CarConfig config) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.SELECT_MANY)) {
         st.setInt(1, config.getId());

         try (ResultSet rs = st.executeQuery()) {
            List<CarComponent> list = new ArrayList<>();

            while (rs.next()) {
               CarComponent comp = new CarComponent();
               comp.setId(rs.getInt("component_id"));
               comp.setName(rs.getString("name"));
               comp.setDescription(rs.getString("description"));
               Money m = new Money(rs.getBigDecimal("base_price"));
               comp.setBasePrice(m);
               comp.setType(CarComponentType.valueOf(rs.getString("type")));
               list.add(comp);
            }

            return list;
         }
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO car_component"
              + " (type_id, name, description, base_price)"
              + " VALUES ((SELECT id FROM car_component_type WHERE type = ?), ?, ?, ?)";

      static final String SELECT_MANY
              = "SELECT component_id, c.name, c.description, c.base_price, t.type"
              + " FROM car_config_component"
              + " LEFT JOIN car_component c ON c.id = component_id"
              + " LEFT JOIN car_component_type t ON t.id = c.type_id"
              + " WHERE config_id = ?";
   }
}
