package domain;

public class Person {
   private int id;
   private String firstName, lastName;
   private String street;
   private PostalCode postalCode;
   private int phone;
   private String email;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getFullName() {
      return firstName + " " + lastName;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getStreet() {
      return street;
   }

   public void setStreet(String street) {
      this.street = street;
   }

   public PostalCode getPostalCode() {
      return postalCode;
   }

   public void setPostalCode(PostalCode postalCode) {
      this.postalCode = postalCode;
   }

   public int getPhone() {
      return phone;
   }

   public void setPhone(int phone) {
      this.phone = phone;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
}
