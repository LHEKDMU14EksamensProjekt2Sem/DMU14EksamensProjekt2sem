package data.access;

import domain.Person;

import java.sql.SQLException;

public interface PersonAccess {
   void createPerson(Person person) throws SQLException;

   void updatePerson(Person person) throws SQLException;
}
