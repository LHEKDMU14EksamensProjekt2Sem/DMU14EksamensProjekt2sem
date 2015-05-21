package data.access;

import domain.PostalCode;

import java.sql.SQLException;
import java.util.List;

public interface PostalCodeAccess {
   void createPostalCodes(List<PostalCode> postalCodes) throws SQLException;
}
