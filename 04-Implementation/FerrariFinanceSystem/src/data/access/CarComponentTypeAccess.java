package data.access;

import java.sql.SQLException;
import java.util.List;

public interface CarComponentTypeAccess {
   void createCarComponentTypes(List<String> types) throws SQLException;
}
