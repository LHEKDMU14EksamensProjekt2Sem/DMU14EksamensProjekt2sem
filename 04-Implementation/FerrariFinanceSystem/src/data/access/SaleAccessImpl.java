package data.access;

import domain.Sale;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Statement.*;

public class SaleAccessImpl implements SaleAccess {
   private ConnectionHandler con;

   public SaleAccessImpl(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void createSale(Sale sale) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(
              SQL.INSERT_ONE, RETURN_GENERATED_KEYS)) {
         st.setInt(1, sale.getSeller().getId());
         st.setInt(2, sale.getCustomer().getId());
         st.setBigDecimal(3, sale.getSellingPrice().asBigDecimal());
         st.setBigDecimal(4, sale.getDiscount().asBigDecimal());
         st.executeUpdate();

         try (ResultSet rs = st.getGeneratedKeys()) {
            rs.next();
            sale.setId(rs.getInt(1));
         }
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO sale(seller_id, customer_id, selling_price, discount)"
              + "VALUES (?, ?, ?, ?)";
   }
}
