package logic.command;

import domain.LoanOffer;
import logic.service.LoanOfferServiceImpl;

import java.sql.SQLException;
import java.util.concurrent.Callable;

public class CreateLoanOfferCommand implements Callable<Void> {
   private final LoanOffer offer;

   public CreateLoanOfferCommand(LoanOffer offer) {
      this.offer = offer;
   }

   @Override
   public Void call() throws SQLException {
      new LoanOfferServiceImpl().createLoanOffer(offer);
      return null;
   }
}
