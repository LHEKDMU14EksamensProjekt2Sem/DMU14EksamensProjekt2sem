package logic.session.requestloan;

import java.util.Observer;

import ui.requestloan.CustomerDetailsPanel;

public interface CustomerDetailsController {
	/**
	 * Denne metode returnerer al information om den pågaeldende kunde
	 * @return Kundeinformation, der matcher cpr
	 */
	public Object getCustomer();

	// TODO: javadoc
	void fetchCreditworthiness(Observer observer);

	void specifyFirstName(String firstName);

	void specifyLastName(String lastName);

	void specifyStreet(String street);

	static void specifyPostalCode(String postalCode) {
			if(postalCode.matches("[0-9]{4}")) {
				
				System.out.println("OK");
				
			}
	}

	void specifyPhone(int phone);

	void specifyEmail(String email);

	/**
	 * Denne metode skal resette datakernen
	 */
	public void reset();
	
	/**
	 * Denne metode skal gemme den indtastede information i databasen og skifte til næste skaermbillede
	 */
	public void saveAndNext();
}
