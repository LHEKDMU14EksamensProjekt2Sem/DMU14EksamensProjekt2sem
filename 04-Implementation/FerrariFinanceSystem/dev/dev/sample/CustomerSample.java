package dev.sample;

import domain.Customer;
import domain.Person;

import java.util.Arrays;
import java.util.List;

import static dev.sample.PersonSample.*;

public class CustomerSample {
   private static final boolean
           GOOD_STANDING = true,
           BAD_STANDING = false;

   // Customers
   //////////////

   public static List<Customer> newCustomers() {
      return Arrays.asList(
              newCustomer(
                      GOOD_STANDING,
                      newPerson("Hero", "Man",
                              "Marble 5", newPostalCode(1000),
                              55532877, "hero@marble.com")),
              newCustomer(
                      GOOD_STANDING,
                      newPerson("Julem", "Anden",
                              "Coladiditvej 102", newPostalCode(2412),
                              97851992, "jule@and.com")));
   }

   private static Customer newCustomer(boolean standing, Person p) {
      Customer c = new Customer();
      c.setStanding(standing);
      c.setPerson(p);
      return c;
   }

   // Customer CPRs
   //////////////////

   public static List<String> newCustomerCPRs() {
      return Arrays.asList(
              "1010101010",
              "1212121212");
   }
}
