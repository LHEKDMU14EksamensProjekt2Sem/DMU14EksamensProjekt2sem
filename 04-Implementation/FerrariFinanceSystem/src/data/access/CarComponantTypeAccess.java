package data.access;

import java.sql.SQLException;
import java.util.List;
import domain.CarComponantType;

public interface CarComponantTypeAccess {
   void createCarComponant(List<CarComponantType> carComponantType) throws SQLException;

   List<CarComponantType> listCarComponantTypes() throws SQLException;
}
