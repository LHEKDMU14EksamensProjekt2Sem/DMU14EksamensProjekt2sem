package data.access;

import java.sql.SQLException;
import java.util.List;

import domain.CarComponant;

public interface CarComponantAccess {
   void createCarComponant(List<CarComponant> carComponant) throws SQLException;

   List<CarComponant> listCarComponants() throws SQLException;
}
