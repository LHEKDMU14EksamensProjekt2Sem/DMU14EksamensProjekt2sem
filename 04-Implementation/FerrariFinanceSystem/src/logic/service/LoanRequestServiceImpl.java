package logic.service;

import data.ConnectionService;
import data.access.LoanRequestAccessImpl;
import data.access.SaleAccessImpl;
import domain.Customer;
import domain.Employee;
import domain.Identity;
import domain.LoanOffer;
import domain.LoanRequest;
import domain.Sale;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LoanRequestServiceImpl implements LoanRequestService {
   @Override
   public void createLoanRequest(LoanRequest loanRequest, ConnectionHandler con) throws SQLException {
      new SaleAccessImpl(con).createSale(loanRequest.getSale());
      new LoanRequestAccessImpl(con).createLoanRequest(loanRequest);
   }

   @Override
   public void createLoanRequest(LoanRequest loanRequest) throws SQLException {
      ConnectionService.execute(con ->
              createLoanRequest(loanRequest));
   }

   @Override
   public List<LoanRequest> listLoanRequests() throws SQLException {
      return null;
   }

   @Override
   public void approveLoanRequest(LoanRequest loanRequest) throws SQLException {

   }

   @Override
   public void declineLoanRequest(LoanRequest loanRequest) throws SQLException {

   }

   @Override
   public Optional<LoanOffer> submitLoanRequest(LoanRequest loanRequest,
                                                Optional<Identity> identity) throws SQLException {
      try (ConnectionHandler con = ConnectionService.connect()) {
         try {
            Optional<LoanOffer> result = submitLoanRequest(loanRequest, identity, con);
            con.commit();
            return result;
         } catch (SQLException e) {
            con.rollback();
            throw e;
         }
      }
   }

   private Optional<LoanOffer> submitLoanRequest(LoanRequest loanRequest,
                                                 Optional<Identity> identity,
                                                 ConnectionHandler con) throws SQLException {
      Sale sale = loanRequest.getSale();
      Employee employee = sale.getSeller();
      employee.getPerson().setId(1);
      if (identity.isPresent()) {
         Customer customer = sale.getCustomer();
         new CustomerServiceImpl().createCustomer(customer, identity.get());
      }

      new SaleAccessImpl(con).createSale(sale);
      new LoanRequestAccessImpl(con).createLoanRequest(loanRequest);

      return Optional.empty();
   }
}
