package dev.sample;

import domain.Employee;
import domain.Person;
import domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserSample {
   public static List<User<Employee>> newUsers(List<Employee> employees) {
      List<User<Employee>> users = new ArrayList<>();
      for (Employee em : employees) {
         Person p = em.getPerson();
         String username = (p.getFirstName().substring(0, 2).toLowerCase()
                 + p.getLastName().replace(" ", "").substring(0, 2).toLowerCase());
         users.add(newUser(em, username));
      }
      return users;
   }

   private static User<Employee> newUser(Employee employee, String username) {
      User<Employee> user = new User<Employee>();
      user.setEntity(employee);
      user.setUsername(username);
      return user;
   }

   public static List<char[]> newPasswords() {
      return Arrays.asList(
              "foobar".toCharArray(),
              "bazqux".toCharArray());
   }
}
