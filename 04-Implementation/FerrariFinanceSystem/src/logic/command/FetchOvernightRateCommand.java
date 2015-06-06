package logic.command;

import com.ferrari.finances.dk.bank.InterestRate;

import java.util.concurrent.Callable;

public class FetchOvernightRateCommand implements Callable<Double> {
   @Override
   public Double call() throws Exception {
      return (InterestRate.i().todaysRate() / 100);
   }
}
