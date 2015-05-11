package logic;

public interface StartController {

	/**
	 * Denne metode tager username og password som parametre, holder dem oppe imod hinande, og hvis de matcher, oprettes der en instans af salesman
	 * @param username
	 * @param password
	 */
	public void login(String username, String password);
	
	/**
	 * Denne metode returnerer saelgerens navn og niveau
	 * @return name og level
	 */
	public String getUser(Object salesman);
}
