package logic.entity;

import domain.Person;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;

public interface PersonLogic {
   void createPerson(Person person, String cpr, ConnectionHandler con) throws SQLException;

   void createPerson(Person person, String cpr) throws SQLException;

   void updatePerson(Person person) throws SQLException;
}
