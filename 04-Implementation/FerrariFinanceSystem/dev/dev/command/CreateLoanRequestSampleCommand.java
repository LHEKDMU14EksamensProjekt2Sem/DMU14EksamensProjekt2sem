package dev.command;

import dev.sample.LoanRequestSample;
import domain.Car;
import domain.Customer;
import domain.Employee;
import domain.LoanRequest;
import logic.service.LoanRequestService;
import logic.service.LoanRequestServiceImpl;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

public class CreateLoanRequestSampleCommand implements Callable<List<LoanRequest>> {
   private final List<Car> cars;
   private final List<Customer> customers;
   private final List<Employee> employees;
   private final ConnectionHandler con;

   public CreateLoanRequestSampleCommand(List<Car> cars,
                                         List<Customer> customers,
                                         List<Employee> employees,
                                         ConnectionHandler con) {
      this.cars = cars;
      this.customers = customers;
      this.employees = employees;
      this.con = con;
   }

   @Override
   public List<LoanRequest> call() throws SQLException {
      return createLoanRequests();
   }

   private List<LoanRequest> createLoanRequests() throws SQLException {
      List<LoanRequest> loanRequests = LoanRequestSample.newLoanRequests(cars, customers, employees);
      LoanRequestService service = new LoanRequestServiceImpl();
      service.createLoanRequests(loanRequests, con);
      return loanRequests;
   }
}
