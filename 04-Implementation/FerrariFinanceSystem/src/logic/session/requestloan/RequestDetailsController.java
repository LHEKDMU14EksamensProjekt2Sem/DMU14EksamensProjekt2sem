package logic.session.requestloan;

import java.util.List;

/**
 * @author Louise
 *
 */
/**
 * @author Louise
 *
 */

/**
 * @author Louise
 *
 */
public interface RequestDetailsController {

	/**
	 * Denne metode henter alle modeller i databasen 
	 */
	public void fetchModels();
	
	/** 
	 * Denne metode returnerer en liste af model-objekter
	 * @return liste af modeller
	 */
	public List getModels();
	
	/**
	 * Denne metode henter alle biler under den pågældende model
	 * @param car
	 */
	public void fetchCars(Object car);
	
	/**
	 * Denne metode returnere en en liste af bil-objekter
	 * @return liste af biler
	 */
	public List getCars();
	
	/**
	 * Denne metode returnerer en samlet salgspris 
	 * @return den aktuelle salgspris
	 */
	public double getSalesprice();
	
	/**
	 * Denne metode registrerer downPayment 
	 */
	public void setDownpayment();
	
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
