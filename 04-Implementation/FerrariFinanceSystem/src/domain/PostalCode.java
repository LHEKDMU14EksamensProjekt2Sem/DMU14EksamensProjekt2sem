package domain;

public class PostalCode {
   private int postalCode;
   private String city;

   public int getPostalCode() {
      return postalCode;
   }

   public void setPostalCode(int postalCode) {
      this.postalCode = postalCode;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   @Override
   public String toString() {
      return postalCode + " " + city;
   }
}
