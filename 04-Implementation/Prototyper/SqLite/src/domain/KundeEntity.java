package domain;

public class KundeEntity extends PersistedEntity<Integer>{
  private int CPR;
  private String firstName;
  private String lastName;
  private String adress;
  private int postalCode;
  private String city;
  private int phone;
  private String email;
  
  public KundeEntity( int CPR, String firstName, String lastName, String adress, int postalCode, String city, int phone, String email ) {
    super();
    this.CPR = CPR;
    this.firstName = firstName;
    this.lastName = lastName;
    this.adress = adress;
    this.postalCode = postalCode;
    this.city = city;
    this.phone = phone;
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName( String firstName ) {
    this.firstName = firstName;
    setChanged(true);
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName( String lastName ) {
    this.lastName = lastName;
    setChanged(true);
  }

  public String getAdress() {
    return adress;
  }

  public void setAdress( String adress ) {
    this.adress = adress;
    setChanged(true);
  }

  public int getPostalCode() {
    return postalCode;
  }

  public void setPostalCode( int postalCode ) {
    this.postalCode = postalCode;
    setChanged(true);
  }

  public String getCity() {
    return city;
  }

  public void setCity( String city ) {
    this.city = city;
    setChanged(true);
  }

  public int getPhone() {
    return phone;
  }

  public void setPhone( int phone ) {
    this.phone = phone;
    setChanged(true);
  }

  public String getEmail() {
    return email;
  }

  public void setEmail( String email ) {
    this.email = email;
    setChanged(true);
  }

  public int getCPR() {
    return CPR;
  }  
}
