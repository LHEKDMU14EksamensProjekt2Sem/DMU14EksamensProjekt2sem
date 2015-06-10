package logic.service;

import domain.Employee;
import domain.User;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserService {
   void createUsers(List<User<Employee>> users, List<char[]> passwords, ConnectionHandler con) throws SQLException;

   Optional<User<Employee>> login(String username, char[] password) throws SQLException;
}
