package logic.service;

import java.sql.SQLException;

import domain.Identity;
import util.jdbc.ConnectionHandler;
import data.ConnectionService;
import data.access.IdentityAccessImpl;
import data.access.PersonAccessImpl;
import domain.Person;

public class PersonServiceImpl implements PersonService {
	@Override
	public void createPerson(Identity identity, ConnectionHandler con)
			throws SQLException {
		new IdentityAccessImpl(con).createIdentity(identity);
		new PersonAccessImpl(con).createPerson(identity.getPerson());
	}

	@Override
	public void createPerson(Identity identity) throws SQLException {
		ConnectionService.execute(con ->
				  createPerson(identity, con));
	}

	@Override
	public void updatePerson(Person person) throws SQLException {
		ConnectionService.execute(con ->
				  new PersonAccessImpl(con).updatePerson(person));
	}
}
