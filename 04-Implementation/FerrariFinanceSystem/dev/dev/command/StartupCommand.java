package dev.command;

import data.ConnectionService;
import dev.option.Option;
import domain.Car;
import domain.Customer;
import domain.Employee;
import domain.LoanRequest;
import domain.User;
import logic.command.CreateDatabaseCommand;
import logic.util.DataUtil;
import util.jdbc.ConnectionHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

public class StartupCommand implements Callable<Void> {
   /**
    * Sets up the database and stores initial data. If a database file
    * already exists, and <code>Option.DESTROY</code> is set to <code>false</code>,
    * this is a no-op. CAUTION: If <code>Option.DESTROY</code> is set to
    * <code>true</code>, any existing database will be destroyed and a new one created.
    * If <code>Option.SAMPLE</code> is <code>true</code>, a fictional data sample
    * will also be created.
    *
    * @throws IOException
    * @throws SQLException
    */
   @Override
   public Void call() throws IOException, SQLException {
      if (Option.DESTROY.get())
         DataUtil.destroyDatabase();

      if (!DataUtil.databaseExists()) {
         try (ConnectionHandler con = ConnectionService.connect()) {
            try {
               new CreateDatabaseCommand(con).call();

               if (Option.SAMPLE.get()) {
                  List<Employee> employees = new CreateEmployeeSampleCommand(con).call();
                  List<User<Employee>> users = new CreateUserSampleCommand(employees, con).call();
                  List<Customer> customers = new CreateCustomerSampleCommand(con).call();
                  List<Car> cars = new CreateCarSampleCommand(con).call();
                  List<LoanRequest> loanRequests =
                          new CreateLoanRequestSampleCommand(cars, customers, employees, con).call();
               }

               con.commit();
            } catch (SQLException e) {
               con.rollback();
               throw e;
            }
         }
      }

      return null;
   }
}
