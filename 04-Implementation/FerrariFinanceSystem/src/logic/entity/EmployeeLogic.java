package logic.entity;

import java.sql.SQLException;

import util.jdbc.ConnectionHandler;
import data.ConnectionHandlerFactory;
import data.access.EmployeeAccess;
import domain.Employee;

public class EmployeeLogic {
	public void createEmployee(Employee employee, String cpr,
			ConnectionHandler con) throws SQLException {
		new PersonLogic().createPerson(employee.getPerson(), cpr, con);
		new EmployeeAccess(con).createEmployee(employee);
	}

	public void createEmployee(Employee employee, String cpr)
			throws SQLException {
		try (ConnectionHandler con = ConnectionHandlerFactory.create()) {
			try {
				createEmployee(employee, cpr, con);
				con.commit();
			} catch (SQLException e) {
				con.rollback();
				throw e;
			}
		}
	}

	public Employee readEmployee(String id, ConnectionHandler con)
			throws SQLException {
		Employee employee = new Employee();
		return employee;
	}
}
