package dev.sample;

import domain.Person;
import domain.PostalCode;

public class PersonSample {
   // Persons
   // Require postal codes
   /////////////////////////

   public static Person newPerson(String first, String last,
                                   String street, PostalCode pc,
                                   int phone, String email) {
      Person p = new Person();
      p.setFirstName(first);
      p.setLastName(last);
      p.setStreet(street);
      p.setPostalCode(pc);
      p.setPhone(phone);
      p.setEmail(email);
      return p;
   }

   // Postal codes
   /////////////////

   public static PostalCode newPostalCode(int postalCode) {
      PostalCode pc = new PostalCode();
      pc.setPostalCode(postalCode);
      return pc;
   }
}
