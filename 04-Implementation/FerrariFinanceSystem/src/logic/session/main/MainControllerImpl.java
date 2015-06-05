package logic.session.main;

import com.ferrari.finances.dk.rki.Rating;
import domain.Identity;
import logic.command.FetchCreditRatingCommand;
import logic.command.FetchOvernightRateCommand;
import logic.format.GeneralDateFormat;
import logic.format.GeneralDateFormatImpl;
import logic.format.GeneralNumberFormat;
import logic.format.GeneralNumberFormatImpl;
import util.swing.SwingCommand;

import java.util.function.Consumer;

public class MainControllerImpl implements MainController {
   private final MainFacade facade;
   private final GeneralNumberFormat numberFormat;
   private final GeneralDateFormat dateFormat;

   public MainControllerImpl(MainFacade facade) {
      this.facade = facade;
      numberFormat = new GeneralNumberFormatImpl();
      dateFormat = new GeneralDateFormatImpl();
   }

   @Override
   public GeneralNumberFormat getGeneralNumberFormat() {
      return numberFormat;
   }

   @Override
   public GeneralDateFormat getGeneralDateFormat() {
      return dateFormat;
   }

   @Override
   public void fetchCreditRating(Identity identity,
                                 Consumer<Rating> resultConsumer,
                                 Consumer<Throwable> exceptionConsumer) {
      new SwingCommand<>(
              new FetchCreditRatingCommand(identity.getCPR()),
              resultConsumer::accept,
              exceptionConsumer::accept
      ).execute();
   }

   @Override
   public void fetchOvernightRate(Consumer<Double> resultConsumer,
                                  Consumer<Throwable> exceptionConsumer) {
      new SwingCommand<>(
              new FetchOvernightRateCommand(),
              resultConsumer::accept,
              exceptionConsumer::accept
      ).execute();
   }
}
