package logic.service;

import domain.LoanOffer;
import util.finance.Money;

import java.sql.Date;

public interface LoanOfferService {
   void createLoanOffer(Date date, Money principal, double interestRate);

   LoanOffer readLoanOffer(int id);
}
