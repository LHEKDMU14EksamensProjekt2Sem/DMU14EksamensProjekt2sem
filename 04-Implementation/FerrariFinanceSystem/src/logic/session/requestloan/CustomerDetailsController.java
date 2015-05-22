package logic.session.requestloan;

public interface CustomerDetailsController {
   void specifyFirstName(String firstName);

   void specifyLastName(String lastName);

   void specifyStreet(String street);

   void specifyPostalCode(String postalCode);

   void specifyPhone(String phone);

   void specifyEmail(String email);

   /**
    * Denne metode skal gemme den indtastede information i databasen
    */
   void saveCustomer();
}
