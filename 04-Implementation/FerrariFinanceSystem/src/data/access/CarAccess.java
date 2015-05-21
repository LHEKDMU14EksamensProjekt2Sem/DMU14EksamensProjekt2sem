package data.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
      ResultSet resultset = null;
      List<CarModel> list;
      try (PreparedStatement st = con.get().prepareStatement(SQL.SELECT_MANY)){
         list = new ArrayList<CarModel>();
         resultset = st.executeQuery();
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
      finally {
         if (resultset != null) {
            resultset.close();
         }
      }
   }
   
   private static class SQL {
      // TODO
      static final String SELECT_ALL = " ";
      
      static final String SELECT_MANY = "SELECT id, year, name, description, base_price FROM car_model";
            
      static final String SELECT_ONE = " ";
   }
}
