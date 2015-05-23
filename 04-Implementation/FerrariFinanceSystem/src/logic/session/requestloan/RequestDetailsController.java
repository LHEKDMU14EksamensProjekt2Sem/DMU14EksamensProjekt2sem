package logic.session.requestloan;

import domain.CarModel;
import util.finance.Money;

public interface RequestDetailsController {
   /**
    * Denne metode henter alle modeller i databasen
    */
   void fetchCarModels();

   /**
    * Denne metode henter alle biler under den pågældende model
    *
    * @param model
    */
   void fetchCars(CarModel model);

   /**
    * Denne metode returnerer en samlet salgspris
    *
    * @return den aktuelle salgspris
    */
   Money getBasePrice();

   void specifyDiscount(String discount);

   void specifyDiscountPct(String discountPct);

   void specifySellingPrice(String sellingPrice);

   /**
    * Denne metode registrerer downPayment
    */
   void specifyDownPayment(String downPayment);

   /**
    * Denne metode returnere det beloeb, der skal finansieres gennem laan
    *
    * @return det samledes beloeb, der skal laanes
    */
   Money getLoanAmount();

   void specifyPreferredRepayment(String prefRepayment);

   void specifyPreferredTerm(String prefTerm);

   /**
    * Denne metode gemmer det indtastede information i databasens
    */
   void sendLoanRequest();
}
