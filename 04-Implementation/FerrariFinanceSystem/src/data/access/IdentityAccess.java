package data.access;

import domain.Identity;

import java.sql.SQLException;

public interface IdentityAccess {
   void createIdentity(Identity identity) throws SQLException;
}
