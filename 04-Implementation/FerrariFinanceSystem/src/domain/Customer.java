package domain;

public class Customer {
   private Person person;
   private boolean standing;

   public int getId() {
      return person.getId();
   }

   public Person getPerson() {
      return person;
   }

   public void setPerson(Person person) {
      this.person = person;
   }

   public boolean inGoodStanding() {
      return standing;
   }

   public void setStanding(boolean standing) {
      this.standing = standing;
   }
}
