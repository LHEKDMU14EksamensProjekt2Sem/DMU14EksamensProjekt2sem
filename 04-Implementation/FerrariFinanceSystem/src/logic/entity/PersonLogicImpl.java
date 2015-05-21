package logic.entity;

import java.sql.SQLException;

import util.jdbc.ConnectionHandler;
import data.ConnectionService;
import data.access.CPRAccess;
import data.access.PersonAccess;
import domain.Person;

public class PersonLogicImpl implements PersonLogic {
	@Override
	public void createPerson(Person person, String cpr, ConnectionHandler con)
			throws SQLException {
		new CPRAccess(con).createCPR(cpr, person);
		new PersonAccess(con).createPerson(person);
	}

	@Override
	public void createPerson(Person person, String cpr) throws SQLException {
		ConnectionService.execute(con ->
				  createPerson(person, cpr, con));
	}

	@Override
	public void updatePerson(Person person) throws SQLException {
		ConnectionService.execute(con ->
				  new PersonAccess(con).updatePerson(person));
	}
}
