package data.access;

import java.sql.SQLException;
import java.util.List;

import domain.CarConfigComponant;

public interface CarConfigComponantAccess {
   void createCarConfigComponant(List<CarConfigComponant> carConfigComponant) throws SQLException;

   List<CarConfigComponant> listCarConfigComponants() throws SQLException;
}
