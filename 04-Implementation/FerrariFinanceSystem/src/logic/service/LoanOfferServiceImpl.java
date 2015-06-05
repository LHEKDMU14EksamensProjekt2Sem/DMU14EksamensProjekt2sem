package logic.service;

import domain.LoanOffer;
import util.finance.Money;

import java.time.LocalDate;

public class LoanOfferServiceImpl implements LoanOfferService {
   @Override
   public void createLoanOffer(LocalDate date, Money principal, double interestRate) {
      LoanOffer offer = new LoanOffer();
      offer.setDate(date);
      offer.setPrincipal(principal);
      offer.setInterestRate(interestRate);


   }

   @Override
   public LoanOffer readLoanOffer(int id) {
      return null;
   }
}
