package logic.entity;

import java.sql.SQLException;

import util.jdbc.ConnectionHandler;
import data.ConnectionHandlerFactory;
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

	@Override
	public void updatePerson(Person person, ConnectionHandler con)
			throws SQLException {
		new PersonAccess(con).updatePerson(person);
	}
}
