package logic.entity;

import data.ConnectionHandlerFactory;
import data.access.CPRAccess;
import data.access.PersonAccess;
import domain.Person;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;

public class PersonLogic {
   public void createPerson(Person person, String cpr, ConnectionHandler con) throws SQLException {
      new CPRAccess(con).createCPR(cpr, person);
      new PersonAccess(con).createPerson(person);
   }

   public void createPerson(Person person, String cpr) throws SQLException {
      try (ConnectionHandler con = ConnectionHandlerFactory.create()) {
         try {
            createPerson(person, cpr, con);
            con.commit();
         } catch (SQLException e) {
            con.rollback();
            throw e;
         }
      }
   }
}
