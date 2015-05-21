package logic.entity;

import domain.Identity;
import domain.Person;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;

public interface PersonLogic {
   void createPerson(Identity identity, ConnectionHandler con) throws SQLException;

   void createPerson(Identity identity) throws SQLException;

   void updatePerson(Person person) throws SQLException;
}
