package data.access;

import domain.Car;
import domain.CarConfig;
import domain.CarModel;
import domain.Sale;
import util.finance.Money;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarAccessImpl implements CarAccess {
   private final ConnectionHandler con;

   public CarAccessImpl(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void createCars(List<Car> cars) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(
              SQL.INSERT_ONE, Statement.RETURN_GENERATED_KEYS)) {
         for (Car car : cars) {
            st.setInt(1, car.getConfig().getId());
            st.executeUpdate();

            try (ResultSet rs = st.getGeneratedKeys()) {
               rs.next();
               car.setId(rs.getInt(1));
            }
         }
      }
   }

   @Override
   public Optional<Car> readCar(int id) throws SQLException {
      return readCar(id, SQL.BY_ID);
   }

   @Override
   public Car readCar(Sale sale) throws SQLException {
      return readCar(sale.getId(), SQL.BY_SALE).get();
   }

   private Optional<Car> readCar(int id, String by) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.selectOne(by))) {
         st.setInt(1, id);

         try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
               Car car = extractCar(rs);
               return Optional.of(car);
            } else {
               return Optional.empty();
            }
         }
      }
   }

   @Override
   public List<Car> listCars(CarModel model) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.SELECT_MANY)) {
         st.setInt(1, model.getId());

         try (ResultSet rs = st.executeQuery()) {
            List<Car> res = new ArrayList<>();
            while (rs.next()) {
               res.add(extractCar(rs));
            }
            return res;
         }
      }
   }

   private Car extractCar(ResultSet rs) throws SQLException {
      Car car = new Car();
      car.setId(rs.getInt("car_id"));

      CarConfig config = new CarConfig();
      config.setId(rs.getInt("config_id"));
      config.setName(rs.getString("config_name"));
      config.setDescription(rs.getString("config_description"));

      CarModel model = new CarModel();
      model.setId(rs.getInt("model_id"));
      model.setYear(rs.getInt("year"));
      model.setName(rs.getString("model_name"));
      model.setDescription(rs.getString("model_description"));
      Money m = new Money(rs.getBigDecimal("base_price"));
      model.setBasePrice(m);

      config.setModel(model);
      car.setConfig(config);
      return car;
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO car(config_id) VALUES (?)";

      static final String SELECT_ONE
              = "SELECT c.id AS car_id, c.config_id,"
              + " cc.model_id, cc.name AS config_name, cc.description AS config_description,"
              + " m.year, m.name AS model_name, m.description AS model_description, m.base_price"
              + " FROM car c"
              + " LEFT JOIN car_config cc ON cc.id = c.config_id"
              + " LEFT JOIN car_model m ON cc.model_id = m.id"
              + " WHERE c.id = %s";

      static final String SELECT_MANY
              = "SELECT c.id AS car_id, c.config_id,"
              + " cc.model_id, cc.name AS config_name, cc.description AS config_description,"
              + " m.year, m.name AS model_name, m.description AS model_description, m.base_price"
              + " FROM car c"
              + " LEFT JOIN car_config cc ON cc.id = c.config_id"
              + " LEFT JOIN car_model m ON cc.model_id = m.id"
              + " WHERE m.id = ?";

      static final String BY_ID = "?";

      static final String BY_SALE
              = "(SELECT car_id FROM sale WHERE id = ?)";

      static String selectOne(String by) {
         return String.format(SELECT_ONE, by);
      }
   }
}
