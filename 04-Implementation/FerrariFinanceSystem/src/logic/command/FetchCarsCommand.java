package logic.command;

import domain.Car;
import domain.CarModel;
import logic.service.CarServiceImpl;

import java.util.List;
import java.util.concurrent.Callable;

public class FetchCarsCommand implements Callable<List<Car>> {
   private final CarModel model;

   public FetchCarsCommand(CarModel model) {
      this.model = model;
   }

   @Override
   public List<Car> call() throws Exception {
      return new CarServiceImpl().listCars(model);
   }
}
