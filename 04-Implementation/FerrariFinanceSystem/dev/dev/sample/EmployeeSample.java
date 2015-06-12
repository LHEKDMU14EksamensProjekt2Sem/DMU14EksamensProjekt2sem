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
                              12345678, "alice@wondermail.org")),
              newEmployee(
                      SALESMAN,
                      newPerson("Bob", "Bazqux",
                              "Foobarvej 164", newPostalCode(8000),
                              88888888, "bobbaz@quxmail.com")));
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
              "0101010101",
              "0202020202");
   }
}
