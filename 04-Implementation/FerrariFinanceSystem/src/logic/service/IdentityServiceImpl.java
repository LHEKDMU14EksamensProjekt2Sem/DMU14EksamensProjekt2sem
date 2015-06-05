package logic.service;

import data.ConnectionService;
import data.access.IdentityAccessImpl;
import domain.Identity;
import domain.Person;

import java.sql.SQLException;

public class IdentityServiceImpl implements IdentityService {
   @Override
   public Identity readIdentity(Person person) throws SQLException {
      return ConnectionService.query(con ->
              new IdentityAccessImpl(con).readIdentity(person));
   }
}
