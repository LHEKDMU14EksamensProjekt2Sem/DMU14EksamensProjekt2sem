package domain;

public class SalesmanEntity extends PersistedEntity<Integer> {
  private String name;
  private String username;
  private String password;
  private int level;
  
  public SalesmanEntity( String name, String username, String password, int level ) {
    super();
    this.name = name;
    this.username = username;
    this.password = password;
    this.level = level;
  }

  public String getName() {
    return name;
  }

  public void setName( String name ) {
    this.name = name;
    setChanged(true);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername( String username ) {
    this.username = username;
    setChanged(true);
  }

  public String getPassword() {
    return password;
  }

  public void setPassword( String password ) {
    this.password = password;
    setChanged(true);
  }

  public int getLevel() {
    return level;
  }

  public void setLevel( int level ) {
    this.level = level;
    setChanged(true);
  }
  
  
  
  
}
