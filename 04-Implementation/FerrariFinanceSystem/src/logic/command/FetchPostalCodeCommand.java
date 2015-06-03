package logic.command;

import domain.PostalCode;
import logic.service.PostalCodeServiceImpl;

import java.util.Optional;
import java.util.concurrent.Callable;

public class FetchPostalCodeCommand implements Callable<Optional<PostalCode>> {
   private final int postalCode;

   public FetchPostalCodeCommand(int postalCode) {
      this.postalCode = postalCode;
   }

   @Override
   public Optional<PostalCode> call() throws Exception {
      // Fake a remote call delay
      Thread.sleep(500);
      return new PostalCodeServiceImpl().readPostalCode(postalCode);
   }
}
