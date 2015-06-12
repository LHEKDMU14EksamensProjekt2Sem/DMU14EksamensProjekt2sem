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
                      newPerson("Charlie", "Sheen",
                              "Strandvejen 55", newPostalCode(2200),
                              55532877, "chsheen@gmail.com")),
              newCustomer(
                      GOOD_STANDING,
                      newPerson("David", "Hasselhoff",
                              "Genvejen 2a", newPostalCode(4000),
                              27851992, "dahassel@hotmail.com")),
              newCustomer(
                      GOOD_STANDING,
                      newPerson("Eric", "Clapton",
                              "Skjoldgade 24", newPostalCode(1000),
                              31249980, "eric@claptonmusic.com")),
              newCustomer(
                      BAD_STANDING,
                      newPerson("Freddy", "Krueger",
                              "Elmegade 13", newPostalCode(5200),
                              13131313, "fred@horrormail.com")));
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
              "0203040506",
              "0304050607",
              "0405060708",
              "0807060504");
   }
}
