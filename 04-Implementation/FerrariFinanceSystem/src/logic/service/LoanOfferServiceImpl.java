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
import java.util.Optional;

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
   public Optional<LoanOffer> readLoanOffer(int id) throws SQLException {
      return ConnectionService.query(con -> {
         Optional<LoanOffer> opt = new LoanOfferAccessImpl(con).readLoanOffer(id);

         LoanRequestService requestService = new LoanRequestServiceImpl();
         LoanOfferPaymentAccess paymentAccess = new LoanOfferPaymentAccessImpl(con);
         if (opt.isPresent()) {
            LoanOffer lo = opt.get();
            lo.setLoanRequest(requestService.readLoanRequest(lo.getId()).get());
            lo.setPayments(paymentAccess.listPayments(lo));
         }

         return opt;
      });
   }

   @Override
   public List<LoanOffer> listLoanOffers() throws SQLException {
      return ConnectionService.query(con -> {
         List<LoanOffer> res = new LoanOfferAccessImpl(con).listLoanOffers();

         LoanRequestService requestService = new LoanRequestServiceImpl();
         for (LoanOffer lo : res) {
            lo.setLoanRequest(requestService.readLoanRequest(lo.getId()).get());
         }

         return res;
      });
   }
}
