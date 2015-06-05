package data.access;

import domain.Identity;
import domain.Person;

import java.sql.SQLException;

public interface IdentityAccess {
   void createIdentity(Identity identity) throws SQLException;

   Identity readIdentity(Person person) throws SQLException;
}
