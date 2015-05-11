package logic;

public interface StartController {

	/**
	 * Denne metode tager username som parameter og holder den oppe mod et password
	 * @param username
	 */
	public void login(String username);
	
	/**
	 * Denne metode returnerer saelgerens navn og niveau
	 * @return name og level
	 */
	public String getSalesman();
}
