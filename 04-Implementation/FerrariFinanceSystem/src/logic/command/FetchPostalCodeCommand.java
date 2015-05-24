package logic.command;

import domain.PostalCode;
import logic.service.PostalCodeServiceImpl;
import util.command.Command;

import java.util.Optional;

public class FetchPostalCodeCommand implements Command<Optional<PostalCode>> {
   private final int postalCode;

   public FetchPostalCodeCommand(int postalCode) {
      this.postalCode = postalCode;
   }

   @Override
   public Optional<PostalCode> execute() throws Exception {
      return new PostalCodeServiceImpl().readPostalCode(postalCode);
   }
}
