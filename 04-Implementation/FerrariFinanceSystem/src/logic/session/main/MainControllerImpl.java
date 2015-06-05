package logic.session.main;

import logic.format.GeneralDateFormat;
import logic.format.GeneralDateFormatImpl;
import logic.format.GeneralNumberFormat;
import logic.format.GeneralNumberFormatImpl;

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
}
