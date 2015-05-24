package data.access;

import domain.CarModel;
import util.finance.Money;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.*;

public class CarModelAccessImpl implements CarModelAccess {
   private ConnectionHandler con;

   public CarModelAccessImpl(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public List<CarModel> listCarModels() throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.SELECT_ALL)) {
         try (ResultSet rs = st.executeQuery()) {
            List<CarModel> list = new ArrayList<>();
            while (rs.next()) {
               CarModel model = new CarModel();
               model.setId(rs.getInt("id"));
               model.setYear(rs.getInt("year"));
               model.setName(rs.getString("name"));
               model.setDescription(rs.getString("description"));
               Money m = new Money(rs.getBigDecimal("base_price"));
               model.setBasePrice(m);
               list.add(model);
            }
            return list;
         }
      }
   }

   @Override
   public void createCarModels(List<CarModel> models) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(
              SQL.INSERT_ONE, RETURN_GENERATED_KEYS)) {
         for (CarModel model : models) {
            st.setInt(1, model.getYear());
            st.setString(2, model.getName());
            st.setString(3, model.getDescription());
            st.setBigDecimal(4, model.getBasePrice().asBigDecimal());
            st.executeUpdate();

            try (ResultSet rs = st.getGeneratedKeys()) {
               rs.next();
               model.setId(rs.getInt(1));
            }
         }
      }
   }

   private static class SQL {
      static final String SELECT_ALL
              = "SELECT id, year, name, description, base_price FROM car_model";

      static final String INSERT_ONE
              = "INSERT INTO car_model"
              + " (year, name, description, base_price)"
              + " VALUES (?, ?, ?, ?)";
   }
}
