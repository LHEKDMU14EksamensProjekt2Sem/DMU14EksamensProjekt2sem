package logic.service;

import data.ConnectionService;
import data.access.EmployeeAccessImpl;
import data.access.UserAccessImpl;
import domain.Employee;
import domain.Person;
import domain.User;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
   @Override
   public void createUsers(List<User<Employee>> users, List<char[]> passwords, ConnectionHandler con) throws SQLException {
      new UserAccessImpl(con).createUsers(users, passwords);
   }

   @Override
   public Optional<User<Employee>> login(String username, char[] password) throws SQLException {
      return ConnectionService.query(con -> {
         Optional<User<Employee>> opt = new UserAccessImpl(con).login(username, password);

         if (opt.isPresent()) {
            User<Employee> user = opt.get();
            Person p = user.getEntity().getPerson();
            Employee em = new EmployeeAccessImpl(con).readEmployee(p.getId()).get();
            user.setEntity(em);
         }

         return opt;
      });
   }
}
