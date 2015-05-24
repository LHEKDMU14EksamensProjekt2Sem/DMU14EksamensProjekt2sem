package domain;

import com.ferrari.finances.dk.rki.Rating;

public class Customer {
   private final String cpr;
   private Rating rating;

   public Customer(String cpr) {
      this.cpr = cpr;
   }

   public String getCPR() {
      return cpr;
   }

   public Rating getRating() {
      return rating;
   }

   public void setRating(Rating rating) {
      this.rating = rating;
   }
}
