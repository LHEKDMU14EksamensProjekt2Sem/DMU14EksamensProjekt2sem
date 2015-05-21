package logic.session.requestloan;

import com.ferrari.finances.dk.rki.Rating;
import domain.Customer;
import domain.Identity;
import logic.command.FetchCreditRatingCommand;
import util.command.Callback;

import java.sql.SQLException;
import java.util.Optional;

public class CPRControllerImpl implements CPRController {
   private final RequestLoanSessionFacade facade;
   private final Identity identity;
   private Customer customer;
   private Rating creditRating;

   public CPRControllerImpl(RequestLoanSessionFacade facade) {
      this.facade = facade;
      identity = new Identity();
   }

   @Override
   public Identity getIdentity() {
      return identity;
   }

   @Override
   public Customer getCustomer() {
      return customer;
   }

   @Override
   public Rating getCreditRating() {
      return creditRating;
   }

   @Override
   public void specifyCPR(String cpr) {
      identity.setCPR(cpr);
   }

   @Override
   public void fetchCustomer(Callback<Optional<Customer>, SQLException> callback) {

   }

   @Override
   public void fetchCreditRating(Callback<Rating, Void> callback) {
      new FetchCreditRatingCommand(facade.getExecutor(), identity.getCPR(), callback).execute();
   }
}
