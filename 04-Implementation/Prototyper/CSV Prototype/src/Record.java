public class Record {
   private String firstName, lastName, email, note;
   private int yearOfBirth, phone;

   public Record(String firstName, String lastName, int yearOfBirth, int phone, String email, String note) {
      setFirstName(firstName);
      setLastName(lastName);
      setYearOfBirth(yearOfBirth);
      setPhone(phone);
      setEmail(email);
      setNote(note);
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

   public int getYearOfBirth() {
      return yearOfBirth;
   }

   public void setYearOfBirth(int yearOfBirth) {
      this.yearOfBirth = yearOfBirth;
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

   public String getNote() {
      return note;
   }

   public void setNote(String note) {
      this.note = note;
   }
}
