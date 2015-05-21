package domain;

public class Identity {
   private String cpr;
   private Person person;

   public String getCpr() {
      return cpr;
   }

   public void setCpr(String cpr) {
      this.cpr = cpr;
   }

   public Person getPerson() {
      return person;
   }

   public void setPerson(Person person) {
      this.person = person;
   }
}
