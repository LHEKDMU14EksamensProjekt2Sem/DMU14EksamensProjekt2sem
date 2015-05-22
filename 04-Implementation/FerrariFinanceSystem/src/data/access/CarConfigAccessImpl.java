package data.access;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.finance.Money;
import util.jdbc.ConnectionHandler;
import domain.CarConfig;
import domain.CarModel;
import domain.Customer;
import domain.Person;
import domain.PostalCode;

public class CarConfigAccessImpl implements CarConfigAccess {

   private ConnectionHandler con;
   
   public CarConfigAccessImpl(ConnectionHandler con) {
      this.con = con;
   }
   
   @Override
   public void createCarConfigs( List<CarConfig> carConfigs ) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE, RETURN_GENERATED_KEYS)) {
         for (CarConfig carConfig : carConfigs) {
            st.setModel(2, carConfig.getModel());
            st.setString(3, carConfig.getName());
            st.setString(4, carConfig.getDescription());
            st.executeUpdate();
           
            try (ResultSet rs = st.getGeneratedKeys()) {
               rs.next();
               carConfig.setId(rs.getInt(1));
            }
         }
      }
   }

   @Override
   public CarConfig readCarConfig( int id ) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.SELECT_ONE)) {
         st.setInt(1, id);

         try (ResultSet rs = st.executeQuery()) {
            CarConfig config = new CarConfig();
            CarModel model = new CarModel();
            config.setModel(model);

            while (rs.next()) {
               config.setId(id);
               config.setModel(rs.getModel("model_id"));
               config.setName(rs.getString("name"));
               config.setDescription(rs.getString("description"));
               model.setId(rs.getInt("id"));
               model.setYear(rs.getInt("year"));
               model.setName(rs.getString("name"));
               model.setDescription(rs.getString("description"));
               Money m = new Money(rs.getBigDecimal("base_price"));
               model.setBasePrice(m);
            }
            return config;

         }
      }
   }

   @Override
   public List<CarConfig> listCarConfigss( CarConfig carConfig ) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.SELECT_ALL)){
         try(ResultSet resultset = st.executeQuery()){
            List<CarConfig> list = new ArrayList<>();
            while (resultset.next()) {
               CarConfig config = new CarConfig();
               config.setId(resultset.getInt("id"));
               config.setModel(resultset.getModel("model"));
               config.setName(resultset.getString("name"));
               config.setDescription(resultset.getString("description"));
               list.add(config);
            }
            return list;
         }
      }
   }
   
   private static class SQL {
      public static String INSERT_ONE;

      static final String SELECT_ALL
                = "SELECT id, year, name, description, base_price FROM car_model";
      
   // TODO Auto-generated method stub
      static final String SELECT_ONE
      = "SELECT id, model_id, name, description"
            + " FROM car_config conf"
            + " WHERE conf.id = ?"
            + " JOIN car_model model"
            + " ON conf.model_id = model.id";
   }

      static final String INSERT_ONE = "INSERT INTO car_config (id, model, name, description) VALUES (?, ?, ?, ?)";
   }
}
