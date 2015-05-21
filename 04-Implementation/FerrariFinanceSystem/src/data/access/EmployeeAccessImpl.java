package data.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import util.jdbc.ConnectionHandler;
import domain.Employee;
import domain.EmployeeRole;
import domain.Person;
import domain.PostalCode;

public class EmployeeAccessImpl implements EmployeeAccess {
	private ConnectionHandler con;

	public EmployeeAccessImpl(ConnectionHandler con) {
		this.con = con;
	}

	@Override
	public void createEmployee(Employee employee) throws SQLException {
		createEmployees(Collections.singletonList(employee));
	}

	@Override
	public void createEmployees(List<Employee> employees) throws SQLException {
		try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
			for (Employee employee : employees) {
				st.setInt(1, employee.getPerson().getId());
				st.setString(2, employee.getRole().toString());
				st.executeUpdate();
			}
		}
	}

	@Override
	public Employee readEmployee(int id) throws SQLException {
		try (PreparedStatement st = con.get().prepareStatement(SQL.SELECT_ONE)) {
			st.setInt(1, id);

			try (ResultSet rs = st.executeQuery()) {
				Employee employee = new Employee();
				Person person = new Person();
				PostalCode postalCode = new PostalCode();

				person.setPostalCode(postalCode);
				employee.setPerson(person);

				while (rs.next()) {
					person.setId(id);
					person.setFirstName(rs.getString("first_name"));
					person.setLastName(rs.getString("last_name"));
					person.setStreet(rs.getString("street"));
					postalCode.setPostalCode(rs.getShort("postal_code"));
					postalCode.setCity(rs.getString("city"));
					person.setPhone(rs.getInt("phone"));
					person.setEmail(rs.getString("email"));
					employee.setRole(EmployeeRole.valueOf(EmployeeRole.class,
							rs.getString("role")));
				}

				return employee;
			}
		}
	}

	private static class SQL {
		static final String INSERT_ONE = "INSERT INTO employee(id, role_id)"
				+ "VALUES (?, (SELECT id FROM employee_role WHERE role = ?))";

		static final String SELECT_ONE = "SELECT id, first_name, last_name, street, postal_code, city, phone, email, role"
				+ " FROM employee e"
				+ " WHERE e.id = ?"
				+ " JOIN person p"
				+ " ON e.id = p.id"
				+ " JOIN postal_code pc"
				+ " ON p.postal_code = pc.postal_code"
				+ " JOIN employee_role er" + "ON e.role_id = er.id";
	}
}
