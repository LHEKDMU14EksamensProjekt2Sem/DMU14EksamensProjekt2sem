package logic.service;

import data.ConnectionService;
import data.access.PostalCodeAccessImpl;
import domain.PostalCode;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PostalCodeServiceImpl implements PostalCodeService {
   @Override
   public void createPostalCodes(List<PostalCode> postalCodes, ConnectionHandler con)
           throws SQLException {
      new PostalCodeAccessImpl(con).createPostalCodes(postalCodes);
   }

   @Override
   public void createPostalCodes(List<PostalCode> postalCodes) throws SQLException {
      ConnectionService.execute(con ->
              createPostalCodes(postalCodes, con));
   }

   @Override
   public Optional<PostalCode> readPostalCode(int postalCode) throws SQLException {
      return ConnectionService.query(con ->
              new PostalCodeAccessImpl(con).readPostalCode(postalCode));
   }
}
