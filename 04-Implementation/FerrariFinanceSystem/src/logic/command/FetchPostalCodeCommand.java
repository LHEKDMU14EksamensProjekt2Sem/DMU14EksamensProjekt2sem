package logic.command;

import domain.PostalCode;
import logic.service.PostalCodeServiceImpl;

import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.Callable;

public class FetchPostalCodeCommand implements Callable<Optional<PostalCode>> {
   private final int postalCode;

   public FetchPostalCodeCommand(int postalCode) {
      this.postalCode = postalCode;
   }

   @Override
   public Optional<PostalCode> call() throws SQLException {
      // Fake a remote call delay
      try {
         Thread.sleep(500);
      } catch (InterruptedException ignore) {
         // No-op
      }
      return new PostalCodeServiceImpl().readPostalCode(postalCode);
   }
}
