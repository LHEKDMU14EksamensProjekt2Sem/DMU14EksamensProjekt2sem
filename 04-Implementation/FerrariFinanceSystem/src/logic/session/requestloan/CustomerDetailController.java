package logic.session.requestloan;

import java.util.Observer;

public interface CustomerDetailController {

	/**
	 * Denne metode skal registrerer et cpr-nummer og henter en instance af customer
	 * @param cpr
	 */
	public void specifyCpr(String cpr);
	
	/**
	 * Denne metode returnerer al information om den pågaeldende kunde
	 * @return Kundeinformation, der matcher cpr
	 */
	public Object getCustomer();

	// TODO: javadoc
	void fetchCreditworthiness(Observer observer);
	
	/**
	 * Denne metode returnerer en by, der matcher det indtastede postnummer 
	 * @param postalCode
	 * @return Bynavn
	 */
	public String getCity(int postalCode);
	
	/**
	 * Denne metode skal resette datakernen
	 */
	public void reset();
	
	/**
	 * Denne metode skal gemme den indtastede information i databasen og skifte til næste skaermbillede
	 */
	public void saveAndNext();
}
