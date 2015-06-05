package logic.command;

import domain.CarModel;
import logic.service.CarModelServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

public class FetchCarModelsCommand implements Callable<List<CarModel>> {
   @Override
   public List<CarModel> call() throws SQLException {
      return new CarModelServiceImpl().listCarModels();
   }
}
