package logic;

public interface CustomerController {

	/**
	 * Denne metode returnerer al information om den pågaeldende kunde
	 * @return Kundeinformation, der matcher cpr
	 */
	public String getCustomer();
	
	/**
	 * Denne metode returnerer kreditvaerdighed i form af bogstaverne A, B, C el. D
	 * @return Kreditvaerdighed som et enkelt bogstav
	 */
	public String getCredit();
	
	/**
	 * Denne metode returnerer en by, der matcher det indtastede postnummer 
	 * @return Bynavn
	 */
	public String getCity();
	
	/**
	 * Denne metode skal resette datakernen
	 */
	public void reset();
	
	/**
	 * Denne metode skal gemme den indtastede information i databasen og skifte til næste skaermbillede
	 */
	public void saveAndNext();
}
