package logic.entity;

import java.sql.SQLException;

import domain.Identity;
import util.jdbc.ConnectionHandler;
import data.ConnectionService;
import data.access.EmployeeAccessImpl;
import domain.Employee;

public class EmployeeLogicImpl implements EmployeeLogic {
	@Override
	public void createEmployee(Employee employee, Identity identity,
			ConnectionHandler con) throws SQLException {
		new PersonLogicImpl().createPerson(identity, con);
		new EmployeeAccessImpl(con).createEmployee(employee);
	}

	@Override
	public Employee readEmployee(int id) throws SQLException {
		Employee employee = new Employee();
		return employee;
	}
}
