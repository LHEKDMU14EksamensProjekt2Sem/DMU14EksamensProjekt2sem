package logic;

public interface LoanRequestController {

	/** 
	 * Denne metode returnerer en liste af bil-objekter
	 * @return liste af biler
	 */
	public String getModels();
	
	/**
	 * Denne metode returnerer en pris paa den valgte model
	 * @return pris paa valgt bil
	 */
	public double getPrice();
	
	/**
	 * Denne metode returnerer informationer på den valgte bil
	 * @return information paa bilen
	 */
	public String getInformation();
	
	/**
	 * Denne metode returnerer en samlet salgspris 
	 * @return den aktuelle salgspris
	 */
	public double getSalesprice();
	
	/**
	 * Denne metode returnerer udbetalingen
	 * @return downpayment
	 */
	public double getDownpayment();
	
	/**
	 * Denne metode returnere det beloeb, der skal finansieres gennem laan
	 * @return det samledes beloeb, der skal laanes
	 */
	public double getFinancing();
	
	/**
	 * Denne metode gemmer det indtastede information i databasens 
	 */
	public void save();
	
}
