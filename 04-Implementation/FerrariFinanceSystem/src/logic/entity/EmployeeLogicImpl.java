package logic.entity;

import java.sql.SQLException;

import util.jdbc.ConnectionHandler;
import data.ConnectionService;
import data.access.EmployeeAccess;
import domain.Employee;

public class EmployeeLogicImpl implements EmployeeLogic {
	@Override
	public void createEmployee(Employee employee, String cpr,
			ConnectionHandler con) throws SQLException {
		new PersonLogicImpl().createPerson(employee.getPerson(), cpr, con);
		new EmployeeAccess(con).createEmployee(employee);
	}

	@Override
	public void createEmployee(Employee employee, String cpr) throws SQLException {
		ConnectionService.execute(con ->
				  createEmployee(employee, cpr, con));
	}

	@Override
	public Employee readEmployee(int id) throws SQLException {
		Employee employee = new Employee();
		return employee;
	}
}
