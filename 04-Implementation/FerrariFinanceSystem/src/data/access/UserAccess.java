package data.access;

import domain.Employee;
import domain.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserAccess {
   void createUsers(List<User<Employee>> users, List<char[]> passwords) throws SQLException;

   void createUser(User<Employee> user, char[] password) throws SQLException;

   Optional<User<Employee>> login(String username, char[] password) throws SQLException;
}
