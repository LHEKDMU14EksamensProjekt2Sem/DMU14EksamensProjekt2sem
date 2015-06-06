package dev.sample;

import domain.Car;
import domain.Customer;
import domain.Employee;
import domain.LoanRequest;
import domain.LoanRequestStatus;
import domain.Sale;
import util.finance.Money;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoanRequestSample {
   // Loan requests
   // Require sales, employees, customers, cars
   //////////////////////////////////////////////

   public static List<LoanRequest> newLoanRequests(List<Car> cars, List<Customer> customers, List<Employee> employees) {
      List<LoanRequest> list = new ArrayList<>();

      // Create a loan request for each customer
      int i = 0;
      for (Customer c : customers) {
         Sale s = newSale(cars.get(i * 10), c, employees.get(i));

         LoanRequest lr = new LoanRequest();
         lr.setSale(s);

         LocalDate now = LocalDate.now();
         int lastYear = now.getYear() - 1;
         Random rand = new Random();
         int month = rand.nextInt(12) + 1;
         int dayOfMonth = rand.nextInt(29) + 1;
         LocalDate date = LocalDate.of(lastYear, month, dayOfMonth);
         lr.setDate(date);

         lr.setStatus(LoanRequestStatus.PENDING);
         lr.setStatusByEmployee(employees.get(i));
         lr.setPreferredTerm(60 * (i + 1));
         lr.setLoanAmount(new Money(s.getSellingPrice().doubleValue() * 0.60));
         list.add(lr);

         i++;
      }

      return list;
   }

   private static Sale newSale(Car car, Customer customer, Employee seller) {
      Sale s = new Sale();
      s.setSeller(seller);
      s.setCustomer(customer);
      s.setCar(car);
      s.setBasePrice(car.getBasePrice());
      s.setSellingPrice(car.getBasePrice());
      return s;
   }
}
