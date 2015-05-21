package data.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.sql.Statement.*;
import util.finance.Money;
import domain.CarModel;
import util.jdbc.ConnectionHandler;

// TODO
public class CarAccess {
   private ConnectionHandler con;
   
   public CarAccess(ConnectionHandler con) {
      this.con = con;
   }
   
   public List<CarModel> readCarModels() throws SQLException {
      List<CarModel> list;
      try (PreparedStatement st = con.get().prepareStatement(SQL.SELECT_MANY)){
         try(ResultSet resultset = st.executeQuery()){
            list = new ArrayList<CarModel>();
            while (resultset.next()) {
               CarModel model = new CarModel();
               model.setId(resultset.getInt("id"));
               model.setYear(resultset.getInt("year"));
               model.setName(resultset.getString("name"));
               model.setDescription(resultset.getString("description"));
               Money m = new Money(resultset.getBigDecimal("base_price"));
               model.setBasePrice(m);
               list.add(model);
            }
            return list;
         }
      }
   }
   
   public void createEmployee(CarModel carModel) throws SQLException {
      createCarModel(Arrays.asList(carModel));
   }
   
   public void createCarModel(List<CarModel> carModels) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE, RETURN_GENERATED_KEYS)) {
         for (CarModel carModel : carModels) {
            st.setInt(2, carModel.getYear());
            st.setString(3, carModel.getName());
            st.setString(4, carModel.getDescription());
            st.setBigDecimal(5, carModel.getBasePrice().asBigDecimal());
            st.executeUpdate();
           
            try (ResultSet rs = st.getGeneratedKeys()) {
               rs.next();
               carModel.setId(rs.getInt(1));
            }
         }
         
      }
   }
   
   private static class SQL {
      // TODO
      static final String SELECT_ALL = " ";
      
      static final String SELECT_MANY = "SELECT id, year, name, description, base_price FROM car_model";
            
      static final String SELECT_ONE = " ";
      
      static final String INSERT_ONE 
               = "INSERT INTO car_model"
               + "(id, year, name, description, base_price)"
               + "VALUES (?, ?, ?, ?, ?)";;
   }
}
