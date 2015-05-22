package logic.session.requestloan;

import java.util.Observer;

import ui.requestloan.CustomerDetailsPanel;

public interface CustomerDetailsController {
	/**
	 * Denne metode returnerer al information om den pågaeldende kunde
	 * @return Kundeinformation, der matcher cpr
	 */
	Object getCustomer();

	void specifyFirstName(String firstName);

	void specifyLastName(String lastName);

	void specifyStreet(String street);

	void specifyPostalCode(String postalCode);

	void specifyPhone(String phone);

	void specifyEmail(String email);

	/**
	 * Denne metode skal resette datakernen
	 */
	void reset();
	
	/**
	 * Denne metode skal gemme den indtastede information i databasen og skifte til næste skaermbillede
	 */
	void saveAndNext();
}
