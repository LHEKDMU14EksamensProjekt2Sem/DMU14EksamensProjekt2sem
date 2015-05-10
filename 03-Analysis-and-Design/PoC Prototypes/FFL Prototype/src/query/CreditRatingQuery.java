package query;

import com.ferrari.finances.dk.rki.CreditRator;
import com.ferrari.finances.dk.rki.Rating;
import domain.Customer;

public class CreditRatingQuery extends AbstractQuery {
   private final CreditRator rator;
   private final Customer customer;

   public CreditRatingQuery(CreditRator rator, Customer customer, long timeout) {
      super(timeout);
      this.rator = rator;
      this.customer = customer;
   }

   @Override
   public void run() {
      Rating r = rator.rate(customer.getCPR());
      customer.setRating(r);
   }
}
