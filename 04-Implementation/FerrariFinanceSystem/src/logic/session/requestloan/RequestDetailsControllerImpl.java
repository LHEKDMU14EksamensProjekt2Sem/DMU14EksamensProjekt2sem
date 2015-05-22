package logic.session.requestloan;

import domain.CarConfig;
import domain.CarModel;
import util.finance.Money;

import java.util.HashMap;
import java.util.Map;

public class RequestDetailsControllerImpl implements RequestDetailsController {
   private final RequestLoanFacade facade;
   private final Map<Integer, CarModel> carModels;
   private final Map<Integer, CarConfig> carConfigs;

   public RequestDetailsControllerImpl(RequestLoanFacade facade) {
      this.facade = facade;
      carModels = new HashMap<>();
      carConfigs = new HashMap<>();
   }

   @Override
   public void fetchCarModels() {

   }

   @Override
   public void fetchCars(CarModel model) {

   }

   @Override
   public Money getBasePrice() {
      return null;
   }

   @Override
   public void specifyDiscount(String discount) {

   }

   @Override
   public void specifyDiscountPct(String discountPct) {

   }

   @Override
   public void specifySellingPrice(String sellingPrice) {

   }

   @Override
   public void specifyDownPayment(String downPayment) {

   }

   @Override
   public Money getLoanAmount() {
      return null;
   }

   @Override
   public void specifyPreferredRepayment(String prefRepayment) {

   }

   @Override
   public void specifyPreferredTerm(String prefTerm) {

   }

   @Override
   public void sendLoanRequest() {

   }
}
