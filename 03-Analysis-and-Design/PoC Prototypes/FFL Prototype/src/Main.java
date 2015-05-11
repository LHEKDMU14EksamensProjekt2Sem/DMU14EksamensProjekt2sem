import com.ferrari.finances.dk.bank.InterestRate;
import com.ferrari.finances.dk.rki.CreditRator;
import domain.Customer;
import domain.LoanRequest;
import query.CreditRatingQuery;
import query.InterestRateQuery;
import query.Query;
import query.QueryManager;
import query.QueryTimeoutException;

public class Main {
   public static void main(String[] args) {
      Customer c = new Customer("1234567890");
      LoanRequest lr = new LoanRequest();

      CreditRator rator = CreditRator.i();
      InterestRate rate = InterestRate.i();

      Query crQuery = new CreditRatingQuery(rator, c, 3500);
      Query irQuery = new InterestRateQuery(rate, lr, 1500);

      QueryManager mgr = new QueryManager();
      mgr.addQuery(crQuery);
      mgr.addQuery(irQuery);

      try {
         mgr.runAll();
         mgr.waitForAll();
         System.out.println("Customer rating: " + c.getRating());
         System.out.println("Today's interest rate: " + lr.getInterestRate());
      } catch (QueryTimeoutException e) {
         Query q = e.getQuery();
         if (q == crQuery)
            System.out.println("Credit rating query timed out");
         else if (q == irQuery)
            System.out.println("Interest rate query timed out");
      }
   }
}
