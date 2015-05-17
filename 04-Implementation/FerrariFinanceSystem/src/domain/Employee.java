package domain;

import logic.EmployeeRole;

public class Employee {
   private Person person;
   private EmployeeRole role;

   public int getId() {
      return person.getId();
   }

   public Person getPerson() {
      return person;
   }

   public void setPerson(Person person) {
      this.person = person;
   }

   public EmployeeRole getRole() {
      return role;
   }

   public void setRole(EmployeeRole role) {
      this.role = role;
   }
}
