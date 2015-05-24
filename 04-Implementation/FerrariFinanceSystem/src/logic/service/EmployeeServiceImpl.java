package logic.service;

import java.sql.SQLException;

import domain.Identity;
import util.jdbc.ConnectionHandler;
import data.access.EmployeeAccessImpl;
import domain.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	@Override
	public void createEmployee(Employee employee, Identity identity,
			ConnectionHandler con) throws SQLException {
		new PersonServiceImpl().createPerson(identity, con);
		new EmployeeAccessImpl(con).createEmployee(employee);
	}

	@Override
	public Employee readEmployee(int id) throws SQLException {
		Employee employee = new Employee();
		return employee;
	}
}
