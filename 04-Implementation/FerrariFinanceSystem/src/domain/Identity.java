package domain;

public class Identity {
   private String cpr;
   private Person person;

   public String getCPR() {
      return cpr;
   }

   public void setCPR(String cpr) {
      this.cpr = cpr;
   }

   public Person getPerson() {
      return person;
   }

   public void setPerson(Person person) {
      this.person = person;
   }
}
