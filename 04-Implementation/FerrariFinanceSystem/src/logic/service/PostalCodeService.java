package logic.service;

import data.access.PostalCodeAccess;
import domain.PostalCode;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;

public interface PostalCodeService extends PostalCodeAccess {
   void createPostalCodes(List<PostalCode> postalCodes, ConnectionHandler con) throws SQLException;
}
