package logic.service;

import domain.LoanOffer;
import util.finance.Money;

import java.time.LocalDate;

public interface LoanOfferService {
   void createLoanOffer(LocalDate date, Money principal, double interestRate);

   LoanOffer readLoanOffer(int id);
}
