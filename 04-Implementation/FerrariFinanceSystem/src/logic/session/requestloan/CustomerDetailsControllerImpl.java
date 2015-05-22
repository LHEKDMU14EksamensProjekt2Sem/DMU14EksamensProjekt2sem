package logic.session.requestloan;

import domain.Customer;

public class CustomerDetailsControllerImpl implements CustomerDetailsController {
   private final RequestLoanFacade facade;
   private Customer customer;

   public CustomerDetailsControllerImpl(RequestLoanFacade facade) {
      this.facade = facade;
   }

   @Override
   public void specifyFirstName(String firstName) {
      if (firstName.matches("[a-zA-Z]+")) {

         System.out.println("OK");
      }
   }

   @Override
   public void specifyLastName(String lastName) {
      if (lastName.matches("[a-zA-Z]+")) {

         System.out.println("OK");
      }
   }

   @Override
   public void specifyStreet(String street) {
      if (street.matches("[a-zA-Z]+")) {

         System.out.println("OK");
      }
   }

   @Override
   public void specifyPostalCode(String postalCode) {
      if (postalCode.matches("[0-9]{4}")) {

         System.out.println("OK");
      }
   }

   @Override
   public void specifyPhone(String phone) {
      if (phone.matches("[0-9]")) {

         System.out.println("OK");
      }
   }

   @Override
   public void specifyEmail(String email) {
      if (email.matches("[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {

         System.out.println("OK");
      }
   }

   @Override
   public void saveCustomer() {
      // TODO Auto-generated method stub

   }
}
