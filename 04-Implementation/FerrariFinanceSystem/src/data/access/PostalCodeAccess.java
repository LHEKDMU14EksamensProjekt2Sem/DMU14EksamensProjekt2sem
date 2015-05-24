package data.access;

import domain.PostalCode;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PostalCodeAccess {
   void createPostalCodes(List<PostalCode> postalCodes) throws SQLException;

   Optional<PostalCode> readPostalCode(int postalCode) throws SQLException;
}
