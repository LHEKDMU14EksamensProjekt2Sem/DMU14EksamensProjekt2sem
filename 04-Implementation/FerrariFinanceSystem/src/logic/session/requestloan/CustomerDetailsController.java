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

	static void specifyFirstName(String firstName) {
		if(firstName.matches("[a-zA-Z]+")) {
			
			System.out.println("OK");
		}
	}

	static void specifyLastName(String lastName) {
		if(lastName.matches("[a-zA-Z]+")) {
			
			System.out.println("OK");
		}
	}

	static void specifyStreet(String street) {
		if(street.matches("[a-zA-Z]+")) {
			
			System.out.println("OK");
		}
	}

	static void specifyPostalCode(String postalCode) {
		if(postalCode.matches("[0-9]{4}")) {
				
			System.out.println("OK");
		}
	}

	static void specifyPhone(String phone) {
		if(phone.matches("[0-9]")) {
			
			System.out.println("OK");
		}
	}

	static void specifyEmail(String email) {
		if(email.matches("[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {
			
			System.out.println("OK");
		}
	}

	/**
	 * Denne metode skal resette datakernen
	 */
	public void reset();
	
	/**
	 * Denne metode skal gemme den indtastede information i databasen og skifte til næste skaermbillede
	 */
	public void saveAndNext();
}
