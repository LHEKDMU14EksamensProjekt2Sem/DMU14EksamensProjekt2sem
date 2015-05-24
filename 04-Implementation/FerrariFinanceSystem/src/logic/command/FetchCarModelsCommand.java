package logic.command;

import domain.CarModel;
import logic.service.CarModelServiceImpl;
import util.command.Command;

import java.util.List;

public class FetchCarModelsCommand implements Command<List<CarModel>> {
   @Override
   public List<CarModel> execute() throws Exception {
      return new CarModelServiceImpl().listCarModels();
   }
}
