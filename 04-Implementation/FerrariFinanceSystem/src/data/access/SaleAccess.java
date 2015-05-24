package data.access;

import domain.Sale;

import java.sql.SQLException;

public interface SaleAccess {
   void createSale(Sale sale) throws SQLException;
}
