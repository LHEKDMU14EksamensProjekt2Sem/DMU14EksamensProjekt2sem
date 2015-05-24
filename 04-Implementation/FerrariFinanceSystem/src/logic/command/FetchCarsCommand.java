package logic.command;

import domain.Car;
import domain.CarModel;
import logic.service.CarServiceImpl;
import util.command.Command;

import java.util.List;

public class FetchCarsCommand implements Command<List<Car>> {
   private final CarModel model;

   public FetchCarsCommand(CarModel model) {
      this.model = model;
   }

   @Override
   public List<Car> execute() throws Exception {
      return new CarServiceImpl().listCars(model);
   }
}
