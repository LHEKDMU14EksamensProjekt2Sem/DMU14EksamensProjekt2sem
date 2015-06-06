package logic.service;

import data.ConnectionService;
import data.access.LoanOfferAccessImpl;
import data.access.LoanOfferPaymentAccess;
import data.access.LoanOfferPaymentAccessImpl;
import data.access.LoanRequestAccess;
import data.access.LoanRequestAccessImpl;
import domain.LoanOffer;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class LoanOfferServiceImpl implements LoanOfferService {
   @Override
   public void createLoanOffers(List<LoanOffer> loanOffers, ConnectionHandler con) throws SQLException {
      new LoanOfferAccessImpl(con).createLoanOffers(loanOffers);

      LoanOfferPaymentAccess paymentAccess = new LoanOfferPaymentAccessImpl(con);
      LoanRequestAccess requestAccess = new LoanRequestAccessImpl(con);
      for (LoanOffer offer : loanOffers) {
         paymentAccess.createPayments(offer);
         requestAccess.updateLoanRequestStatus(offer.getLoanRequest());
      }
   }

   @Override
   public void createLoanOffer(LoanOffer loanOffer) throws SQLException {
      ConnectionService.execute(con ->
              createLoanOffers(Collections.singletonList(loanOffer), con));
   }

   @Override
   public List<LoanOffer> listLoanOffers() throws SQLException {
      return ConnectionService.query(con ->
              new LoanOfferAccessImpl(con).listLoanOffers());
   }
}
