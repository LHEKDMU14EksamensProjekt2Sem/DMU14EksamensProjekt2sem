package dev.sample;

import domain.Employee;
import domain.EmployeeRole;
import domain.Person;

import java.util.Arrays;
import java.util.List;

import static dev.sample.PersonSample.*;
import static domain.EmployeeRole.*;

public class EmployeeSample {
   // Employees
   //////////////

   public static List<Employee> newEmployees() {
      return Arrays.asList(
              newEmployee(
                      SALES_MANAGER,
                      newPerson("Alice", "Wunderbaum",
                              "Wonderland 42", newPostalCode(7400),
                              12345678, "alice@wonderland.com")),
              newEmployee(
                      SALESMAN,
                      newPerson("Bob", "da Bass",
                              "Bassheadvej 164", newPostalCode(7800),
                              88888888, "bob@mixcloud.com")));
   }

   private static Employee newEmployee(EmployeeRole role, Person p) {
      Employee em = new Employee();
      em.setRole(role);
      em.setPerson(p);
      return em;
   }

   // Employee CPRs
   //////////////////

   public static List<String> newEmployeeCPRs() {
      return Arrays.asList(
              "9876543210",
              "0099887766");
   }
}
